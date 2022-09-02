package com.example.smartypants

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.smartypants.databinding.DetailActivityBinding
import com.example.smartypants.databinding.FragmentDefinitionBinding
import com.example.smartypants.databinding.FragmentLetterListBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DefinitionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DefinitionFragment : Fragment(), TextToSpeech.OnInitListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var tts: TextToSpeech? = null
    private var _binding: FragmentDefinitionBinding? = null
    private val binding get() = _binding!!
    private lateinit var letterId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    /**
     * speakOut() function will speak the definitionText
     */
    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(context,"This language is not supported",Toast.LENGTH_LONG)
            }
        }else   {
            Log.d("TTS","Initialization failed")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDefinitionBinding.inflate(inflater,container,false)
        val view = binding.root

        tts = TextToSpeech(context,this)
        binding.definitionSound.setOnClickListener {
            speakOut(binding.definitionWord.text.toString())
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DefinitionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DefinitionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onDestroyView() {
        _binding = null
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroyView()

    }
}