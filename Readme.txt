Technical requrements for Multi Language Search(find name for brand):

1*10^9 users in total
100 000 users active on simultaniously 

Architecture?

Java Server?


Step by step:
1) Visitor must select languages(next feature - countries) to search from on the first screen(save selection in cookies or another good place)
2) Visitor enters search phrase on his/her main language
    - phrase will be translated automatically to languages selected previously
    - MLS will perform search for every translation and for original phrase
3) List of results is shown in very usable and convinient form in the browser with paging. 
   - paging (first/last << < 1 2 3 4 ... n > >>  next/previous )
