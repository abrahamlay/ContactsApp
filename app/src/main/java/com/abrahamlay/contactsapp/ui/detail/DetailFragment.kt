package com.abrahamlay.contactsapp.ui.detail

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.abrahamlay.contactsapp.R
import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.ui.BaseFragment
import com.abrahamlay.contactsapp.ui.widget.DialogComposer
import com.abrahamlay.contactsapp.ui.widget.GeneralListener
import com.abrahamlay.contactsapp.ui.widget.SnackbarComposer
import com.abrahamlay.contactsapp.util.Constant
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by Abraham on 18/10/2018.
 */
class DetailFragment : BaseFragment(),DetailContract.DetailView{

    private val presenter: DetailPresenter by inject{ parametersOf(this) }
    companion object {
        fun newInstance(contact: DataItem): DetailFragment {
            val args = Bundle()
            args.putParcelable(Constant.PARAM_OBJECT, contact )


            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private lateinit var name: String

    private var contact: DataItem? =null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        contact=arguments?.getParcelable<DataItem>(Constant.PARAM_OBJECT)

        contact.let {
            name="${contact?.firstName} ${contact?.lastName}"
            tv_name.text = name
            tv_age.text= it?.age.toString()
        }

        initEditButton()
    }

    private fun initEditButton() {
        val actionEdit = (activity as DetailActivity).actionEdit
        actionEdit?.setOnClickListener {
            DialogComposer.showWriteDialog(activity as Activity,
                getString(R.string.edit),
                getString(R.string.edit_your_contact),contact,
                object : GeneralListener.OnClickListener{
                    override fun onClick(data: Any) {
                        val person=data as DataItem
                        editContact(contact?.id,person)
                    }

                })
        }
    }

    private fun editContact(id:String?,data: DataItem){
        presenter.editContact(id,data)
    }

    override fun onContactEdited(message: String?, data: DataItem) {
        activity?.setResult(Activity.RESULT_OK)
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
        updateDetail(data)
    }

    override fun onContactLoaded(data: DataItem) {
        updateDetail(data)
    }

    override fun showLoading(active: Boolean) {

    }

    private fun updateDetail(data: DataItem){
        name="${data.firstName} ${data.lastName}"
        tv_name.text = name
        tv_age.text= data.age.toString()
        (activity as DetailActivity).supportActionBar?.title = name
        (activity as DetailActivity).showImage(data.photo)
    }

}