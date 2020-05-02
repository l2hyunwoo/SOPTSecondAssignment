package com.example.soptsecondseminarrepeat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_library.*

/**
 * A simple [Fragment] subclass.
 */
class LibraryFragment : Fragment() {
    lateinit var bookAdapter : BookAdapter
    val datas = mutableListOf<BookData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_book.layoutManager = GridLayoutManager(this.context, 3)
        rv_book.addItemDecoration(BookItemDecoration(12))
        bookAdapter = BookAdapter(view.context)
        rv_book.adapter = bookAdapter
        loadDatas()
    }

    private fun loadDatas(){
        datas.apply{
            add(
                BookData(
                    bookName = "데미안",
                    img_book = "https://img.ridicdn.net/cover/509000028/xxlarge"
                )
            )
            add(
                BookData(
                    bookName = "동물농장",
                    img_book = "https://lh3.googleusercontent.com/proxy/Vc9RWob-8dFzpl5js30GS7WwVYFO_ZeYc754NHL5sYz-evFHnd6MyJN46B4ROfAkJgwUm_f2OpoSnQ"
                )
            )
            add(
                BookData(
                    bookName = "참을 수 없는 존재의 가벼움",
                    img_book = "https://lh3.googleusercontent.com/proxy/ZRPgm76OLieyu_wz9UmCQqITqCde6PLbVoWFXUN0aLuSneEAc7uVPowvx8lo9GZd9MslvaH9QjTTDMYpBw6A21Lw"
                )
            )
            add(
                BookData(
                    bookName = "1984",
                    img_book = "https://lh3.googleusercontent.com/proxy/BPKj9FuCPnxEJlglNYr-GwkzFejuhqcwSHbq3839Y4WBeaZha6XxxjS0JRxsQVgrfSYZRRND-XUpY8U"
                )
            )
            add(
                BookData(
                    bookName = "호밀밭의 파수꾼",
                    img_book = "https://lh3.googleusercontent.com/proxy/JUS38hr6Se4B3EssJd3DH-HrGTTZVXZ_6RMXuFPUZK6SRojkeHpS24jr6Y78OKHSd0Z1DVyCUiOGbBh22aS1uqi3CB3p1bEndufUicXDzCY6IauI8xkImA"
                )
            )
            add(
                BookData(
                    bookName = "이방인",
                    img_book = "https://lh3.googleusercontent.com/proxy/Uws6_JhHCwZuzojwEBWx3eg03Zeta6xSqob4aK7QwR1jj8L3qpoURYLRq3ZoDsLUyeoLOQoE1nMZuvl0B2SWj7v6WJO3Q2eIw0cf-832xhRXBTIOWS5odQ"
                )
            )
            add(
                BookData(
                    bookName = "설국",
                    img_book = "https://lh3.googleusercontent.com/proxy/bbgGsXMG6CIOsh5I3fnAwLi6Sc-YReHE44KPJoqi5IXXAczKIes_UMz7J_Cdx6b4NqGFGxP6he8beBSSo7J70vSikcrp51Dsh_87l0FPGbbJh6YuXBDcZQ"
                )
            )
            add(
                BookData(
                    bookName = "페스트",
                    img_book = "https://lh3.googleusercontent.com/proxy/O7bw5SCuVzpAOqsJEEvHfFqISl-tscszIICiPXbTk4mzPOKpC7nEeCz8lD23Gmi1yk1HPAyK50_MoSPHuzZaA9gwZY3TWTRZkuNL9M7cyuluOqUl31Xz_A"
                )
            )
            add(
                BookData(
                    bookName = "등대로",
                    img_book = "https://lh3.googleusercontent.com/proxy/tisH3JsSwYTGWmmzjQEww42p9-p2dMjb3PJ8WXD4gdMnONH5at8gbXuTkw1zVzss0M6Yp0l5dl2e-Jhjx9K5XZ-TlhVvtH01Bo5iiZLwzLU"
                )
            )

        }
        bookAdapter.datas = datas
        bookAdapter.notifyDataSetChanged()
    }

}
