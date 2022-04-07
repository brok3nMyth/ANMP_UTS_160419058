package id.ac.ubaya.anmp_uts_160419058

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.anmp_uts_160419058.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_loan_edit.*

class LoanEditFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private val LoanListAdapter = LoanListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if (arguments != null) {
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            val id = LoanEditFragmentArgs.fromBundle(requireArguments()).id
            viewModel.fetch_loan(id)
            //observeViewModel()
        }
        observeViewModel()

        btnUpdate.setOnClickListener {
            val action = LoanEditFragmentDirections.actionLoanEditToLoanList()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.loansLiveData.observe(viewLifecycleOwner){
            val loan = viewModel.loansLiveData.value
            loan?.let{
                editTextCode3.setText(loan.code)
                txtTitle4.text = loan.title
                txtStatus2.text = loan.status
            }
        }
//        val code = viewModel.loansLiveData.value?.code
//        Log.d("showcode", code.toString())


    }
}