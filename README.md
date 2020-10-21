[![Experimental Project header](https://github.com/newrelic/opensource-website/raw/master/src/images/categories/Experimental.png)](https://opensource.newrelic.com/oss-category/#experimental)

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

## License
[Project Name] is licensed under the [Apache 2.0](http://apache.org/licenses/LICENSE-2.0.txt) License.
>[If applicable: The [project name] also uses source code from third-party libraries. You can find full details on which libraries are used and the terms under which they are licensed in the third-party notices document.]
