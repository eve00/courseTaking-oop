package auth

interface Authenticator {

    fun authenticateAs(): Boolean

    fun authorize():Boolean
}