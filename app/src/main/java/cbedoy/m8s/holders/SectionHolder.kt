package cbedoy.m8s.holders

import android.view.View
import cbedoy.m8s.models.Section
import kotlinx.android.synthetic.main.user_section_cell.*

class SectionHolder (override val containerView: View) : BaseHolder(containerView) {
    override fun reload(any: Any) {
        any as Section

        name_view.text = any.title
    }

}