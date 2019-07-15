package cbedoy.m8s.holders

import android.view.View
import cbedoy.m8s.models.Common
import kotlinx.android.synthetic.main.common_value_cell.*

class ValueHolder (override val containerView: View) : BaseHolder(containerView) {
    override fun reload(any: Any) {
        any as Common.Value

        common_value_cell_title.text = any.title
        common_value_cell_description.text = any.value
    }
}