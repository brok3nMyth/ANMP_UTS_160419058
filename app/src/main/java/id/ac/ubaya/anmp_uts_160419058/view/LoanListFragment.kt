package id.ac.ubaya.anmp_uts_160419058

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.anmp_uts_160419058.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_loan_list.*
import kotlinx.android.synthetic.main.fragment_loan_list.view.*


class LoanListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val loanListAdapter  = LoanListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh_loans()

        txtErrorSearch.visibility= View.GONE
        recLoanView.layoutManager = LinearLayoutManager(context)
        recLoanView.adapter = loanListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recLoanView.visibility = View.GONE

            progressLoadSearch.visibility = View.VISIBLE
            viewModel.refresh_loans()
            refreshLayout.isRefreshing = false
            txtErrorSearch.visibility= View.GONE
        }
        btnAddLoan.setOnClickListener {
            val action = LoanListFragmentDirections.actionLoanListToLoanForm()
            Navigation.findNavController(it).navigate(action)
        }


    }

    fun observeViewModel() {
        viewModel.loansLiveData.observe(viewLifecycleOwner, Observer {
            loanListAdapter.updateLoansList(it)
        })
        viewModel.loansLoadErrorLiveData.observe(viewLifecycleOwner, Observer {
            txtErrorSearch.visibility = if(it) View.VISIBLE else View.GONE

        })
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recLoanView.visibility = View.GONE
                progressLoadSearch.visibility = View.VISIBLE
            } else {
                recLoanView.visibility = View.VISIBLE
                progressLoadSearch.visibility = View.GONE
            }
        })

    }
}