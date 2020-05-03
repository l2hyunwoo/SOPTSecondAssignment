# SOPTSecondAssignment
SOPT 2차 세미나: BottomNavigation, ViewPager, RecyclerView를 활용한 필수, 성장 과제

## CapturedImages
<img src="https://user-images.githubusercontent.com/54518925/80909802-12cfa480-8d66-11ea-9e0a-8db58b878b24.png" width="30%"></img>
<img src="https://user-images.githubusercontent.com/54518925/80909804-1400d180-8d66-11ea-899d-b11b42bfde47.png" width="30%"></img>
<img src="https://user-images.githubusercontent.com/54518925/80909805-15ca9500-8d66-11ea-8909-33581e364286.png" width="30%"></img>
<img src="https://user-images.githubusercontent.com/54518925/80909806-16fbc200-8d66-11ea-971c-6e0d14599cdc.png" width="30%"></img>


## RecyclerView의 아이템 크기는 내 맘대로! itemDecoration
### BookItemDecoration.kt
```
class BookItemDecoration(private val spaceHeight: Int): RecyclerView.ItemDecoration() {
    //getItemOffset을 이용하면 item의 margin 조절 가능
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) { //outRect의 프로퍼티 접근
            if (parent.getChildAdapterPosition(view) == 0) { //맨 위에 있으면 top에 마진 넣기
                top = spaceHeight
            }
            left =  spaceHeight
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}
```

### BookItemDecoration.kt
```
class LibraryFragment : Fragment() {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_book.addItemDecoration(BookItemDecoration(12))
    }
}
```

+ ```RecyclerView.ItemDecoration``` 클래스를 상속받아 RecyclerView의 Outline, Margin 설정 가능

## 난 padding에도 밀리지 않는다, ```@layout/*.xml```에서 ```clipToPadding```만 조절한다면

```
<androidx.recyclerview.widget.RecyclerView
        (중략)
        android:clipToPadding="false"/>
```

+ 전체 뷰에 padding을 걸면 화면 전체에 padding이 걸려 child(프래그먼트 등)영역에 스크롤을 할 때 영향을 줄 수 있다
+ 이때 clipToPadding 속성에 false를 걸게 되면 child 영역에는 영향을 주지 않은 채 전체 영역에 대한 padding만 걸리게 된다

## RecyclerView 만드는 순서
정리 해둬야 나중에 내가 쓸 수 있을 듯

1. 아이템의 형태를 짜 -> 통일된 layout 제작
2. 배열 방향 -> LayoutManager 설정(GridLayout, LinearLayout)
3. 데이터 형태를 결정한다 -> data class 활용

### BookData.kt
```
data class BookData (
    val bookName : String,
    val img_book : String
)
```

4. ViewHolder에 data 묶어주기
### BookViewHolder.kt
```
class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_bookname = itemView.findViewById<TextView>(R.id.tv_bookname)
    val img_book = itemView.findViewById<ImageView>(R.id.img_book)

    fun bind(bookData: BookData){
        tv_bookname.text = bookData.bookName
        Glide.with(itemView).load(bookData.img_book).into(img_book)
    }
}
```

5. Adapter에 연결하기
### BookAdapter.kt
```
class BookAdapter(private val context: Context) : RecyclerView.Adapter<BookViewHolder>() {
    var datas = mutableListOf<BookData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount() : Int{
        return datas.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}
```

6. RecyclerView를 Fragment(or View)에 띄우기
### LibraryFragment.kt
```
class LibraryFragment : Fragment() {
    lateinit var bookAdapter : BookAdapter
    val datas = mutableListOf<BookData>()
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
                    bookName = "",
                    img_book = ""
                )
            )
        }
        bookAdapter.datas = datas
        bookAdapter.notifyDataSetChanged()
        //데이터가 갱신되었음을 어댑터에 알려주는 기능
    }

}
```

+ ```notifyDataSetChanged()```
  + 데이터가 갱신되었음을 어댑터에 알려주는 기능

+ manifest.xml에서 user-permission을 INTERNET으로 연결해야 이미지를 인터넷에서 따올 수 있음
```<uses-permission android:name="android.permission.INTERNET"/>```
