package cbedoy.m8s.holders

import android.view.View
import cbedoy.m8s.models.Common
import kotlinx.android.synthetic.main.common_switch_cell.*

class SwitchHolder (override val containerView: View) : BaseHolder(containerView) {
    override fun reload(any: Any) {
        any as Common.Switch

        common_switch_cell_title.text = any.title
        common_switch_cell_state.isSelected = any.value
    }
}