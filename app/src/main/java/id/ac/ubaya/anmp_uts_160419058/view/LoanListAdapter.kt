package id.ac.ubaya.anmp_uts_160419058

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.anmp_uts_160419058.model.Books
import id.ac.ubaya.anmp_uts_160419058.model.Loans
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.loan_list_item.view.*

class LoanListAdapter (val loanList:ArrayList<Loans>): RecyclerView
.Adapter<LoanListAdapter.LoanViewHolder>() {
    class LoanViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.loan_list_item, parent, false)
        return LoanViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        val loan = loanList[position]
        with(holder.view){
            txtCode3.text = loan.code
            txtTitle3.text = loan.title
            txtStatus.text = loan.status


            cardLoan.setOnClickListener {
                val action = LoanListFragmentDirections.actionLoanListToLoanEdit(loan.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

    override fun getItemCount() = loanList.size

    fun updateLoansList(newLoanList: ArrayList<Loans>){
        loanList.clear()
        loanList.addAll(newLoanList)
        notifyDataSetChanged()
    }
}