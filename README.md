# MySeftApp
Code of mine

## Lost and Found app
- Languge use: Kotlin Android

----------------------
## Base class
 Model: MVVM (In creating process)
 
 #### UI <-> ViewModel <-> Repository <-> Database or Api
 
 #### Tech used: 
 - ViewModel, LiveData: inprogress
 - Retrofit,
 - Glide
 - Room: done
 - Dagger
 - Rx (???)
 
### 1. UI: Inprogress
#### a. Base Activity: use ViewModel, (Rx??)
- getLayoutId(): layout for the activity
- initViewModelClass(): init viewmodel class
- onCreateLayout(): create layout, init data, ... (in onCreate() method)
- addView()
- replaceView()
- removeView()
- showLoading()
- hideLoading():

#### b. Base Fragment
- getLayoutId(): layout for the fragment
- initViewModelClass(): init viewmodel class
- onCreateLayout(): create layout, init data, ... (in onViewCreated() method)
- onAttach()
- onDestroyView()
- showLoading(): ???
- hideLoading(): ???


### 2. ViewModel: 06/02/2019
### 3. Repository
### 4. Database and Api

