# ijext-sample-project
Example implementation of library extension loading in Scala plugin for IntelliJ.
This project is made up of three sub-projects: [the library](https://github.com/mutcianm/ijext-sample-project/tree/master/library/src/main/scala/org/jetbrains/scala/libextensions/test) we want to support in IJ,
[implementation](https://github.com/mutcianm/ijext-sample-project/tree/master/ijext/src/main) of given support and a [sandbox project](https://github.com/mutcianm/ijext-sample-project/tree/master/test-project/src/main/scala) for ad-hoc testing in the editor.


## Running
- Import project in IntelliJ
- Enable extensions loading under `File | Settings | Languages & Frameworks | Scala | Extensions`
- Run `support/publishLocal` task in SBT, this will publish both the library and the support for it
- Uncomment sandbox project and re-import the build
