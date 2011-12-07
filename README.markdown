Fixes imports and other API changes between Richfaces Selenium and Arquillian Ajocado

USAGE
=====

-Build and run the main class of class Resolver with one parameter, which points to the parent directory of all files which should be opened and fixed.

-e.g. the parameter can look like /pathToProjectFromRoot/project/module/src/main/java/org/richfaces/tests/metamer/ftest

-what is fixed can be easily customized by altering the maps which contains key value pairs, what is going to be replaced by what respectively

-which files should be opened and refactored is determined by method Resolver.willBeThisFileFixed
