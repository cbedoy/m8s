package cbedoy.m8s.models

import android.view.View
import android.widget.CompoundButton

interface Common {
    data class Switch (val title: String, val value: Boolean, val listener: CompoundButton.OnCheckedChangeListener)

    data class Value (val title: String, val value: String, val listener: View.OnClickListener?){
        constructor(title: String, value: String) : this(title, value, null)
    }

    data class Title (val title: String)

    class Margin ()

    data class TitleDescription (val title: String, val description: String, val listener: View.OnClickListener?){
        constructor(title: String, value: String) : this(title, value, null)
    }

    data class Credit(val listener: View.OnClickListener?){
        constructor() : this(null)
    }
}