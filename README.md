[![New Relic Experimental header](https://github.com/newrelic/opensource-website/raw/master/src/images/categories/Experimental.png)](https://opensource.newrelic.com/oss-category/#new-relic-experimental)

# New Relic Java Instrumentation for Tibco Service Grid
   
Provides instrumentation for the Tibco Service Grid product

## Installation

1. Download the lastest release
2. Extract the jars from the archive
3. Copy the jars into the extensions directory of the New Relic Java Agent.  Create the directory if it does not exist.
4. Restart the application

## Getting Started
Requires gradle installed.   
Run the following to build agent dependencies.  
gradle checkForDependencies.  

## Building
Set the environment variable NEW_RELIC_EXTENSIONS_DIR to the directory where you want to build the extensions
To build all extensions, run  
gradle clean install  
  
To build a particular extension, run the following where *project* is the extension to build   
gradle *project*:clean *project*:install.  
   
**Example**  
gradle tibco_sg_spline:clean tibco_sg_spline:install

## Testing

Not Supported

## Support

New Relic has open-sourced this project. This project is provided AS-IS WITHOUT WARRANTY OR DEDICATED SUPPORT. Issues and contributions should be reported to the project here on GitHub.

## Contributing
New Relic has open-sourced this project. This project is provided AS-IS WITHOUT WARRANTY OR DEDICATED SUPPORT. Issues and contributions should be reported to the project here on GitHub.

We encourage you to bring your experiences and questions to the [Explorers Hub](https://discuss.newrelic.com) where our community members collaborate on solutions and new ideas.

**A note about vulnerabilities**

As noted in our [security policy](../../security/policy), New Relic is committed to the privacy and security of our customers and their data. We believe that providing coordinated disclosure by security researchers and engaging with the security community are important means to achieve our security goals.

If you believe you have found a security vulnerability in this project or any of New Relic's products or websites, we welcome and greatly appreciate you reporting it to New Relic through [HackerOne](https://hackerone.com/newrelic).
## License
New Relic Java Instrumentation for Tibco Service Grid is licensed under the [Apache 2.0](http://apache.org/licenses/LICENSE-2.0.txt) License.

