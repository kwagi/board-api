import{S as M,i as P,s as R,e as f,t as B,c as L,a as m,b as v,k as d,y as S,x as z,n as F,d as y,z as U,m as q,r as j,v as A,o as E,p as G,w as H,D as I}from"./index.e6052a71.js";function J(s){let r,a,i,x,o,D,w,n,l,p,C,h,k,c,b,t,_;return{c(){r=f("div"),a=B("\uC81C\uBAA9: "),i=f("input"),x=L(),o=f("div"),D=f("p"),D.textContent="\uB0B4\uC6A9\uC785\uB825",w=L(),n=f("textarea"),l=L(),p=f("div"),C=B(`maximum 100MB
    `),h=f("input"),k=L(),c=f("div"),b=f("button"),b.textContent="\uB4F1\uB85D",m(i,"type","text"),m(r,"class","main"),m(n,"rows","10"),m(n,"class","content"),m(o,"class","main"),m(h,"type","file"),m(p,"class","text-center"),m(b,"class","btn btn-primary"),m(c,"class","main")},m(e,u){v(e,r,u),d(r,a),d(r,i),S(i,s[1]),v(e,x,u),v(e,o,u),d(o,D),d(o,w),d(o,n),S(n,s[0]),v(e,l,u),v(e,p,u),d(p,C),d(p,h),s[6](h),v(e,k,u),v(e,c,u),d(c,b),t||(_=[z(i,"input",s[4]),z(n,"input",s[5]),z(b,"click",s[3])],t=!0)},p(e,[u]){u&2&&i.value!==e[1]&&S(i,e[1]),u&1&&S(n,e[0])},i:F,o:F,d(e){e&&y(r),e&&y(x),e&&y(o),e&&y(l),e&&y(p),s[6](null),e&&y(k),e&&y(c),t=!1,U(_)}}}function K(s,r,a){let i,x,o,D;q(s,j,t=>a(7,i=t)),q(s,A,t=>a(8,x=t)),q(s,E,t=>a(9,o=t)),q(s,G,t=>a(10,D=t));const{email:w}=D;let n="",l="",p;const C=`http://${o}:8080/api/board/post`;function h(){if(x===!1)return alert("\uBA3C\uC800 \uB85C\uADF8\uC778 \uD558\uC138\uC694."),i("/");const t=new FormData;t.append("email",w),t.append("title",n),t.append("contents",l),t.append("files",p.files[0]),H.post(C,t).then(()=>{i("/")}).catch(_=>{_.response.data[0]?alert(_.response.data[0].message):_.response.data.headers&&alert(_.response.data.headers.message)})}function k(){n=this.value,a(1,n)}function c(){l=this.value,a(0,l)}function b(t){I[t?"unshift":"push"](()=>{p=t,a(2,p)})}return s.$$.update=()=>{s.$$.dirty&1&&l.length>1e3&&(alert("1000\uC790\uB97C \uB118\uAE38\uC218\uC5C6\uC2B5\uB2C8\uB2E4."),a(0,l=l.substring(0,1e3)))},[l,n,p,h,k,c,b]}class O extends M{constructor(r){super(),P(this,r,K,J,R,{})}}export{O as default};
