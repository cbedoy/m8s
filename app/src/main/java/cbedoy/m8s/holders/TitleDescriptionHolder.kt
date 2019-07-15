package cbedoy.m8s.holders

import android.view.View
import cbedoy.m8s.models.Common
import kotlinx.android.synthetic.main.common_title_description_cell.*

class TitleDescriptionHolder (override val containerView: View) : BaseHolder(containerView) {
    override fun reload(any: Any) {
        any as Common.TitleDescription

        common_title_description_title.text = any.title
        common_title_description_value.text = any.description

    }
}