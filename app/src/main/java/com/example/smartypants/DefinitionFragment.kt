package com.example.smartypants

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.smartypants.database.Definitions

import com.example.smartypants.databinding.FragmentDefinitionBinding
import com.example.smartypants.viewmodel.DefinitionViewModelFactory
import com.example.smartypants.viewmodel.DefinitionsViewModel
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

    companion object{
        var LETTER = "letter"
    }

    private var tts: TextToSpeech? = null
    private var _binding: FragmentDefinitionBinding? = null
    private val binding get() = _binding!!
    private lateinit var letter: String

    private val viewModel: DefinitionsViewModel by activityViewModels {
        DefinitionViewModelFactory(
            (activity?.application as DefinitionsApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            letter = it.getString(LETTER).toString()
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
                Toast.makeText(context,"This language is not supported",Toast.LENGTH_LONG).show()
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

            /*val definition = Definitions(23,"A","Apple","/ˈapəl/", "the round fruit of a tree of the rose family, which typically has thin red or green skin and crisp flesh.")
            viewModel.insertDefinition(definition)*/
            binding.addDefinitionButton.setOnClickListener {
                    addDefinition()
                Toast.makeText(requireContext(),"Definition Added!",Toast.LENGTH_LONG).show()
            }
        }
        return view
    }


    private fun addDefinition(){
        val letter = binding.definitionWord.text.toString()
        val word = binding.definitionWord.text.toString()
        val pronounce = binding.definitionPronunce.text.toString()
        val definition = binding.definitionText.text.toString()

        val fullDefinition = Definitions(3,letter,word,pronounce,definition)
        viewModel.insertDefinition(fullDefinition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDefinition(binding.definitionWord.toString())
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