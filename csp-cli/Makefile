GOCMD=go
BINARY_NAME=csp-cli

all: clean lint test build

build:
	go build -o bin/$(BINARY_NAME) ./cmd/

clean:
	rm -rf bin

lint:
	golint ./...

test:
	go test ./...
