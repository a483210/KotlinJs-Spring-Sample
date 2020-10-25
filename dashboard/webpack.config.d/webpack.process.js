function writeEnv(env, params) {
    Object.keys(params).forEach(function (key) {
        parse(env, key, params[key])
    });
}

function parse(env, prefix, params) {
    Object.keys(params).forEach(function (key) {
        let prefixKey = prefix + "." + key

        let obj = params[key]
        if (obj instanceof Object) {
            parse(prefixKey, obj)
        } else {
            env[prefixKey] = JSON.stringify(obj)
        }
    });
}