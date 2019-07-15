package cbedoy.m8s.holders

import android.view.View
import cbedoy.m8s.models.Common
import kotlinx.android.synthetic.main.common_title_cell.*

class TitleHolder (override val containerView: View) : BaseHolder(containerView) {
    override fun reload(any: Any) {
        any as Common.Title

        common_title_cell_title.text = any.title
    }
}