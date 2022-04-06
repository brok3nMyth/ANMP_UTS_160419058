package id.ac.ubaya.anmp_uts_160419058

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import id.ac.ubaya.anmp_uts_160419058.IntroFragment
import id.ac.ubaya.anmp_uts_160419058.R
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.fragment_intro.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class IntroFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtSambutan.text = "Selamat datang di ULIS (Ubaya Library Information System)"
        btnMulai.setOnClickListener {
            val action = IntroFragmentDirections.actionIntroToMain()
            Navigation.findNavController(it).navigate(action)
        }
    }

}