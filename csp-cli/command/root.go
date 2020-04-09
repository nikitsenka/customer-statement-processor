package command

import (
	"github.com/eda/csp/cli/api"
	"github.com/eda/csp/cli/log"
	"github.com/spf13/cobra"
	"github.com/spf13/viper"
	"os"
)

// rootCmd represents the base command when called without any subcommands
var rootCmd = &cobra.Command{
	Short: "Command line interface for  Customer Statement Processor",
	Long: `
      _____  _____ _____         _____ _      _____ 
     / ____|/ ____|  __ \       / ____| |    |_   _|
    | |    | (___ | |__) |_____| |    | |      | |  
    | |     \___ \|  ___/______| |    | |      | |  
    | |____ ____) | |          | |____| |____ _| |_ 
     \_____|_____/|_|           \_____|______|_____|
                                                 

https://github.com/nikitsenka/customer-statement-processor`,
}

var client = api.NewClient()
var apiUrl string

func init() {
	viper.SetConfigFile("config.yml")
	viper.ReadInConfig()
	if viper.GetBool("debug") {
		log.EnableDebug()
	}
	apiUrl = viper.GetString("api.base.url")
}

//Execute root command
func Execute() {
	if err := rootCmd.Execute(); err != nil {
		log.Fatal(err)
		os.Exit(1)
	}
}
