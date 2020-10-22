[![New Relic Experimental header](https://github.com/newrelic/opensource-website/raw/master/src/images/categories/Experimental.png)](https://opensource.newrelic.com/oss-category/#new-relic-experimental)

# Tibco Service Grid Instrumentation

> Provides instrumentation for the Tibco Service Grid product

## Installation

> Download the lastest release
> Extract the jars from the archive
> Copy the jars into the extensions directory of the New Relic Java Agent.  Create the directory if it does not exist.
> Restart the application

## Getting Started
> Requires gradle installed.
> Run the following to build agent dependencies
gradle checkForDependencies

## Building
> Set the environment variable
> To build all extensions, run  
gradle clean install
  
> To build a particular extension, run the following where *project* is the extension to build
gradle *project*:clean *project*:install

**Example**  
gradle tibco_sg_spline:clean tibco_sg_spline:install

## Testing

> Not Supported

## Support

New Relic hosts and moderates an online forum where customers can interact with New Relic employees as well as other customers to get help and share best practices. Like all official New Relic open source projects, there's a related Community topic in the New Relic Explorers Hub. You can find this project's topic/threads here:

>Add the url for the support thread here

## Contributing
We encourage your contributions to improve [project name]! Keep in mind when you submit your pull request, you'll need to sign the CLA via the click-through using CLA-Assistant. You only have to sign the CLA one time per project.
If you have any questions, or to execute our corporate CLA, required if your contribution is on behalf of a company,  please drop us an email at opensource@newrelic.com.

**A note about vulnerabilities**

As noted in our security policy, New Relic is committed to the privacy and security of our customers and their data. We believe that providing coordinated disclosure by security researchers and engaging with the security community are important means to achieve our security goals.

If you believe you have found a security vulnerability in this project or any of New Relic's products or websites, we welcome and greatly appreciate you reporting it to New Relic through HackerOne.

If you would like to contribute to this project, review these guidelines.

To all contributors, we thank you! Without your contribution, this project would not be what it is today. We also host a community project page dedicated to Project Name.
## License
[Project Name] is licensed under the [Apache 2.0](http://apache.org/licenses/LICENSE-2.0.txt) License.
>[If applicable: The [project name] also uses source code from third-party libraries. You can find full details on which libraries are used and the terms under which they are licensed in the third-party notices document.]
