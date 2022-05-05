<br class="Apple-interchange-newline"/>
<h1 align="center">
  <a href="https://github.com/sdxqw/ClientTweaker">ClientTweaker</a>
</h1>

<h4 align="center">A crazy template for jarmodding 1.8.9 with tweaker client in 2022!</h4>

<div align="center">
    <img src="https://img.shields.io/github/last-commit/sdxqw/ClientTweaker" alt="GitHub last commit"/>
    <img src="https://img.shields.io/badge/MC-1.8.9-brightgreen.svg" alt="Minecraft"/>
    <br>
    <img src="https://img.shields.io/github/v/release/sdxqw/ClientTweaker.svg" alt="Release"/>
    <img src="https://img.shields.io/github/languages/code-size/sdxqw/ClientTweaker" alt="GitHub code size in bytes"/>
    <img src="https://img.shields.io/tokei/lines/github/sdxqw/ClientTweaker"/>
    <br>
</div>

<h3 align="center">
    <a href="#get-start">Get start</a> •
    <a href="#key-features">Key Features</a> •
    <a href="#thanks">Thanks</a> •
    <a href="#contributing">Contributing</a> •
</h3>

## Get start

If you are here you need to setup our project!. Please follow this steps:

* Open the file "build.gradle"
* 1 - Change the line 19 with your group id ex: "io.github.sdxqw"
* 2 - Change the line 21 with your modname (should be the name of the jar)
* 2.1 - Change the line 32 "-Xms6G" and "-Xmx6G" to how much ram you want to host!
* 3 - Change the line 22 with your tweaker name or client name (will be the most important part of this setup)
* 4 - Change the line 29 only "io.github.sdxqw.launch.Tweaker" to your tweaker dir!
* 5 - Change the line 88 "io.github.sdxqw.launch.Tweaker" to your tweaker dir!
* 6 - Change the file name "mixins.tweaker.json" to "mixins.TWEAKER-OR-CLIENT-NAME-STEP-3.json" please use the tweaker or client name in step 3!
* Open the file "mixins.tweaker.json"
* 7 - Change the line 4 with your package where mixins should be.
* 7.1 - Change the line 5 "mixins.tweaker.refmap.json" to "mixins.TWEAKER-OR-CLIENT-NAME-STEP-3.refmap.json" please use the tweaker or client name in step 3!
* 8 - Now the client should work! have fun learing mixins!
* 9 - If the client wont start make sure to set the package to MAIN and not API. (IJ IDEA)

## Key Features

* Free themplate!
* Works in 2022!
* Allow people make their client with 1.8.9 in 2022!

# Thanks

thanks to xcfrg for repo!

## Contributing

Want to help improve ClientTweaker?, but how?:

* by making an fork of the project
* Sharing this repo
* Help improve the guide

---
<h6 align="center">
  | GitHub - <a href="https://github.com/sdxqw">@sdxqw</a> 
  |
</h6>
