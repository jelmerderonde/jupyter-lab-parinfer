[![Build Status](https://travis-ci.com/jelmerderonde/jupyter-lab-parinfer.svg?branch=master)](https://travis-ci.com/jelmerderonde/jupyter-lab-parinfer)

# Jupyter Lab Parinfer

jupyter-lab-parinfer is a [Jupyter Lab](https://jupyterlab.readthedocs.io/en/stable/index.html) Parinfer extension to enable [Parinfer](https://shaunlebron.github.io/parinfer/) (structural editing for Lisps) in Jupyter Code Cells.

It uses the excellent [Parinfer](https://shaunlebron.github.io/parinfer/) to make writing s-expressions easier. Because Jupyter Code cells use [CodeMirror](https://codemirror.net/) by default, this extension simply adds the already written [parinfer-codemirror](https://github.com/shaunlebron/parinfer-codemirror) plugin to the mix.

## Installation
Use the Jupyter Lab labextension command:

```bash
jupyter labextension install jupyter-lab-parinfer
```

You can also use the [Extension Manager](https://jupyterlab.readthedocs.io/en/stable/user/extensions.html#using-the-extension-manager) to install jupyter-lab-parinfer.

## Usage
The extension should work automatically if you are using a Notebook which has "clojure" set as the default language (as is the case with the [IClojure](https://github.com/HCADatalab/IClojure) Kernel). Right now there are no configuration options available and Parinfer is always set to Ident mode. This might change in the future (or with a pull request from you?).

## Development
The actual extension (small as it is) is written in ClojureScript and compiles to Javascript using [shadow-cljs](http://shadow-cljs.org/). For development you will need the following prerequisites:

* [Clojure](https://clojure.org/guides/getting_started)
* [Node.js / NPM](https://nodejs.org/en/download/)
* [shadow-cljs](http://shadow-cljs.org/)

Run the following commands to start developing:
```bash
npm run dev
```

And in a new terminal (but still in the directory where this extension lives):
```bash
jupyter labextension link .
jupyter lab --watch
```

The extension will automatically compile every time you make a change to the code. Refresh your browser to see the results.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[GNU AGPLv3](https://choosealicense.com/licenses/agpl-3.0/)