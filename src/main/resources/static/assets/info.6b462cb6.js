import{S as G,i as J,s as K,e,c as a,t as N,a as u,b as _,k as t,n as E,d as $,m as F,p as O,C as P}from"./index.71fbe89e.js";function Q(s){let r,f,o,l,i,C,D,h,m,b,S,M,T,j,c,k,w,q,z,v,H,A,I,L,x,p,B,y;return{c(){r=e("div"),r.innerHTML="<h1>\uB0B4 \uC815\uBCF4</h1>",f=a(),o=e("div"),l=e("table"),i=e("tr"),C=e("th"),C.textContent="\uC774\uBA54\uC77C",D=a(),h=e("td"),h.textContent=`${s[1]}`,m=a(),b=e("tr"),S=e("th"),S.textContent="\uC774\uB984",M=a(),T=e("td"),T.textContent=`${s[2]}`,j=a(),c=e("tr"),k=e("th"),k.textContent="\uD734\uB300\uC804\uD654",w=a(),q=e("td"),q.textContent=`${s[3]}`,z=a(),v=e("tr"),H=e("th"),H.textContent="\uAC00\uC785\uC77C",A=a(),I=e("td"),I.textContent=`${s[4].replace("T","::")}`,L=a(),x=e("div"),p=e("a"),B=N("\uD68C\uC6D0\uD0C8\uD1F4"),u(r,"class","main"),u(l,"class","main"),u(p,"class","btn btn-danger"),u(p,"href",y=s[0]("/delete")),u(x,"class","main")},m(n,d){_(n,r,d),_(n,f,d),_(n,o,d),t(o,l),t(l,i),t(i,C),t(i,D),t(i,h),t(l,m),t(l,b),t(b,S),t(b,M),t(b,T),t(l,j),t(l,c),t(c,k),t(c,w),t(c,q),t(l,z),t(l,v),t(v,H),t(v,A),t(v,I),_(n,L,d),_(n,x,d),t(x,p),t(p,B)},p(n,[d]){d&1&&y!==(y=n[0]("/delete"))&&u(p,"href",y)},i:E,o:E,d(n){n&&$(r),n&&$(f),n&&$(o),n&&$(L),n&&$(x)}}}function R(s,r,f){let o,l;F(s,O,m=>f(5,o=m)),F(s,P,m=>f(0,l=m));const{email:i,name:C,phone:D,regDate:h}=o;return[l,i,C,D,h]}class V extends G{constructor(r){super(),J(this,r,R,Q,K,{})}}export{V as default};