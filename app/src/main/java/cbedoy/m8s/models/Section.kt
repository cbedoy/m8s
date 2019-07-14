package cbedoy.m8s.models

class Section : CommonModel {
    lateinit var title: String

    override fun getHolderType(): CommonModel.type {
        return CommonModel.type.section
    }
}