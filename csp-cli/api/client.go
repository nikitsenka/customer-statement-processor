package api

import (
	"bytes"
	"github.com/eda/csp/cli/log"
	"io"
	"io/ioutil"
	"net/http"
	"net/url"
	"time"
)

//NewDefaultClient creates http client
func NewClient() HTTPClient {
	return &http.Client{
		Transport: &logRequestResponse{http.DefaultTransport},
		Timeout:   time.Second * 10,
	}
}

//HTTPClient interface for http client
type HTTPClient interface {
	Do(req *http.Request) (*http.Response, error)
	PostForm(url string, data url.Values) (resp *http.Response, err error)
}

//RoundTrip prints response body to log
func (adt *logRequestResponse) RoundTrip(req *http.Request) (*http.Response, error) {
	req.Body = logBody(req.Body)
	resp, err := adt.T.RoundTrip(req)
	if err != nil {
		log.Fatal(err)
	}
	resp.Body = logBody(resp.Body)
	log.Debug(resp.Status)
	return resp, err
}

func logBody(body io.ReadCloser) io.ReadCloser {
	if body != nil {
		requestBody, readErr := ioutil.ReadAll(body)
		if readErr != nil {
			log.Fatal(readErr)
		}
		log.Debug(string(requestBody))
		return ioutil.NopCloser(bytes.NewBuffer(requestBody))
	}
	return nil
}

type logRequestResponse struct {
	T http.RoundTripper
}

//Post sends POST request to  REST api
func Post(client HTTPClient, url string, body io.Reader, contentType string) {
	request, _ := http.NewRequest("POST", url, body)
	request.Header.Set("Content-Type", contentType)
	resp, err := client.Do(request)
	if err != nil {
		log.Fatal(err, resp.Status)
	}
	handleErrorResponse(resp)
}

func handleErrorResponse(resp *http.Response) {
	switch {
	case resp.StatusCode != 200:
		log.Fatal("Unexpected server error")
	}
}
