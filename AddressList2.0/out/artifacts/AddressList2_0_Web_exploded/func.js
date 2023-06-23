
function showAll(persons){
    try {
        document.querySelector('tbody').parentNode.removeChild(document.querySelector('tbody'))
    } catch (e) {
        console.log(e);
    }
    const table = document.getElementsByTagName('table');
    const tbody = document.createElement('tbody');
    table[0].appendChild(tbody);

    for (let i = 0; i < persons.length; i++) {
        let row = getDataRow(persons[i]);
        tbody.appendChild(row);
}

function getDataRow(person){
    let row = document.createElement('tr');  //创建行
    //把单元格弄进去
    let idCell = document.createElement('td');
    idCell.innerHTML = person.id;
    //设值id不可见
    idCell.style.display = 'none';
    row.appendChild(idCell);
    let nameCell = document.createElement('td');
    nameCell.innerHTML = person.name;
    row.appendChild(nameCell);
    let genderCell = document.createElement('td');
    genderCell.innerHTML = person.gender;
    row.appendChild(genderCell);
    let phoneNumberCell = document.createElement('td');
    phoneNumberCell.innerHTML = person.tel;
    row.appendChild(phoneNumberCell);
    let ageCell = document.createElement('td');
    ageCell.innerHTML = person.age;
    row.appendChild(ageCell);

    //添加功能键
    let functionCell = document.createElement('td');
    row.appendChild(functionCell);
    functionCell.style.width = "155px";

    //删除按钮
    let btnDel = document.createElement('input');
    btnDel.setAttribute('type','button');
    btnDel.setAttribute('value','删除');
    //绑定删除功能
    btnDel.onclick = function () {
        let tname=this.parentNode.parentNode.childNodes[1].innerHTML;
        if (confirm("确定删除"+tname+"?")){
            this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
            let id = this.parentNode.parentNode.childNodes[0].innerHTML;
            Delete(id);
        }
    };
    functionCell.appendChild(btnDel);

    //修改按钮
    let btnUpd = document.createElement('input');
    btnUpd.setAttribute("type","button");
    btnUpd.setAttribute("value","修改");
    //绑定修改功能
    btnUpd.onclick = function () {
        let id = this.parentNode.parentNode.childNodes[0].innerHTML;
        window.location.assign("http://localhost:8080/AddressList2_0_Web_exploded/UpdatePerson?id="+id);
    };
    functionCell.appendChild(btnUpd);

    return row;
}

function CheckAndUpdate(){
    const name=document.getElementById("name").value;
    const id=document.getElementById("id").value;
    const gender=document.getElementsByName("gender");
    const age=document.getElementById("age").value;
    const tel = document.getElementById("tel").value;

    let sGender=gender[0].checked?gender[0].value:gender[1].value;

    if(tel.length!==11||isNaN(tel)){
        alert("请输入11位电话号码");
    }else if (name===""){
        alert("请输入姓名");
    }else if (age==="" || age<=0 || age>200){
        alert("请输入正确的年龄");
    }else if(!(gender[1].checked||gender[0].checked)){
        alert("请选择性别");
    } else{
        const xhttp=new XMLHttpRequest();
        let url="http://localhost:8080/AddressList2_0_Web_exploded/DoUpdate?name="+name+"&gender="+sGender+"&age="+age+"&tel="+tel+"&id="+id;
        xhttp.open("GET",url,true);
        xhttp.send();
        xhttp.onreadystatechange=function () {
            if(this.readyState===4 && this.status===200){
                window.location.assign("http://localhost:8080/AddressList2_0_Web_exploded/getAllPerson");
            }
        }
    }
}
function fuzzySelect() {
    const fName = document.getElementById("fuzzyName").value;
    if(fName!=null && fName!==""){
        const xhttp = new XMLHttpRequest();
        let url="http://localhost:8080/AddressList2_0_Web_exploded/getPersonFuzzy?name="+fName;

        xhttp.open("GET",url,true);
        xhttp.send();
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                let result=this.responseText;
                let persons=eval('('+result+')');
                showAll(persons);
            }
        }
    }else{
        alert("请输入想要查询者的姓名或关键字");
    }
}

function Delete(id) {
    const xhttp = new XMLHttpRequest();
    let url="http://localhost:8080/AddressList2_0_Web_exploded/DeletePerson?id="+id;
    console.log(url);
    xhttp.open("GET",url,true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState===4 && xhttp.status===200){
        }
    }
}
}

