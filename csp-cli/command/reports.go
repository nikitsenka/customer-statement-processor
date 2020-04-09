package command

import (
	"bytes"
	"encoding/csv"
	"encoding/json"
	"github.com/eda/csp/cli/api"
	"github.com/eda/csp/cli/log"
	"github.com/spf13/cobra"
	"io/ioutil"
	"os"
	"path"
	"strings"
)

var processReportsCmd = &cobra.Command{
	Use:     "process reports",
	Short:   "Initiate customer statements processing using inputs from file",
	Example: "./scp-cli process reports --file reports.xml",
	Run:     processReports,
}

var file string

func init() {
	rootCmd.AddCommand(processReportsCmd)
	processReportsCmd.PersistentFlags().StringVarP(&file, "file", "f", "", "Defines the path, name and extension of the input data file")
	processReportsCmd.MarkPersistentFlagRequired("file")
}

type Record struct {
	Reference     string `json:"reference"`
	AccountNumber string `json:"accountNumber"`
	Description   string `json:"description"`
	StartBalance  string `json:"startBalance"`
	Mutation      string `json:"mutation"`
	EndBalance    string `json:"endBalance"`
}

func processReports(cmd *cobra.Command, args []string) {
	ext := getExtension()
	url := apiUrl + "/records/process"
	switch ext {
	case "csv":
		api.Post(client, url, convertCsv(readFile(file)), "application/json")
	case "xml", "json":
		api.Post(client, url, readFile(file), "application/"+ext)
	default:
		log.Fatal("Unsupported file format")
	}

	log.Info("Finished")
}

func getExtension() string {
	openFile, err := os.Open(file)
	if err != nil {
		log.Fatal(err)

	}
	defer openFile.Close()
	return strings.Split(path.Ext(file), ".")[1]
}

func readFile(file string) *bytes.Buffer {
	readFile, err := ioutil.ReadFile(file)
	if err != nil {
		log.Fatal(err)
	}
	data := bytes.NewBuffer(readFile)
	return data
}

func convertCsv(csvBytes *bytes.Buffer) *bytes.Buffer {
	reader := csv.NewReader(csvBytes)
	reader.FieldsPerRecord = 6

	csvData, err := reader.ReadAll()
	if err != nil {
		log.Fatal(err)

	}

	var rec Record
	var recs []Record

	for _, each := range csvData[1:] {
		rec.Reference = each[0]
		rec.AccountNumber = each[1]
		rec.Description = each[2]
		rec.StartBalance = each[3]
		rec.Mutation = each[4]
		rec.EndBalance = each[5]
		recs = append(recs, rec)

	}

	jsonData, err := json.Marshal(recs)
	if err != nil {
		log.Fatal(err)

	}
	return bytes.NewBuffer(jsonData)
}
