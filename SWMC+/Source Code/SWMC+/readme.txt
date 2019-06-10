The default OS is Windows, but you can chang to Linux by change the code (we have considered this and provide the variables to do this).
This is an Eclipse project.
Please follow the following three steps to make the code run:
1. Open Eclipse ,then click:File->Import->General->Existing Projects into Workspace
2. At the line "Select root directory:    Browse...   " click Browse... and select this project,then click Finish.
3. Make sure the dom4j-1.6.1.jar is in the project build path

Now you can open ./src/org.nust.wf.Main/MainFrame.java and run it.

Note, if you want to generate PNG file, you need to install Graphviz.msi in your computer first. Only in this way, can you get the discovered scientific workflow in the SVG format (10.svg, for example). 
The graphviz path in set in the ./src/org.nust.wf.graphviz/GraphViz.java,you could change it to your own path. You also need to create a folder "tmp" under C drive to store temporary files.