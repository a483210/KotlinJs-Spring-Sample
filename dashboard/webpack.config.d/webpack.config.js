const webpack = require('webpack')
const yaml = require('yamljs');

let application = yaml.load('application.yml')

if (application == null) {
    throw 'application.yml not found'
}
if (application.js.buildEnv == null) {
    throw 'buildEnv not found'
}
if (application.js.actionEnv == null) {
    application.js.actionEnv = 'local'
}
let actionEnv = application.js.actionEnv

let applicationEnvFilePath = 'application-' + actionEnv + '.yml';
let applicationEnv = yaml.load(applicationEnvFilePath)
if (applicationEnv == null) {
    throw applicationEnvFilePath + ' not found'
}

let env = {}

writeEnv(env, application)
writeEnv(env, applicationEnv)

config.plugins = [
    new webpack.DefinePlugin({
        'process.env': env
    })
]