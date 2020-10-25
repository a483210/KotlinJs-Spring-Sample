package com.xy.kotlin.sample.server.error

import com.xy.kotlin.sample.server.model.system.IServerError

/**
 * ServerException
 *
 * @author Created by gold on 2020/10/17 17:04
 */
class ServerException(override val code: Int,
                      override val errorMessage: String?
) : RuntimeException(), IServerError {

    constructor(error: IServerError) : this(error.code, error.errorMessage)

    constructor(error: IServerError, errorMessage: String?) : this(error.code, errorMessage)

}