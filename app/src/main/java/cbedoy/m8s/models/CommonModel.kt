package cbedoy.m8s.models

interface CommonModel {
    enum class type{
        section, user
    }

    fun getHolderType() : type
}