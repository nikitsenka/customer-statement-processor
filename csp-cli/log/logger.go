package log

import (
	"github.com/sirupsen/logrus"
	"os"
)

var logger = &logrus.Logger{
	Out:   os.Stdout,
	Level: logrus.InfoLevel,
	Formatter: &logrus.TextFormatter{
		ForceColors:               true,
		DisableColors:             false,
		EnvironmentOverrideColors: false,
		DisableTimestamp:          true,
		FullTimestamp:             false,
		TimestampFormat:           "",
		DisableSorting:            false,
		SortingFunc:               nil,
		DisableLevelTruncation:    true,
		QuoteEmptyFields:          false,
		FieldMap:                  logrus.FieldMap{logrus.FieldKeyMsg: "@message"},
		CallerPrettyfier:          nil,
	},
}

//Fatal wrapper for logger
func Fatal(args ...interface{}) {
	logger.Fatal(args...)
}

//Info wrapper for logger
func Info(args ...interface{}) {
	logger.Info(args...)
}

//Debug wrapper for logger
func Debug(args ...interface{}) {
	logger.Debug(args...)
}

//EnableDebug for logger
func EnableDebug() {
	logger.SetLevel(logrus.DebugLevel)
}
