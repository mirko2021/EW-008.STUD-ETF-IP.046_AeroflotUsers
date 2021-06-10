"UTF-8"

/**
 * Платформа, подаци и функционалности за филтере који се 
 * вежу за филтрирање порука при прегледу код корисника.
 */
 
var msg_filters = []; 
var msg_filters_exclusive = []; 

var msg_filter_choosed = -1; 


function addMF(message_filter){
	if(message_filter===null) return; 
	if(message_filter.trim().length==0) return; 
	msg_filters[msg_filters.length] = message_filter; 
	msg_filters_exclusive[msg_filters_exclusive.length] = true;
	refreshMFList(); 
}

function getMF(position){
	if(position<0) return null; 
	if(position>=msg_filters.length) return null; 
	return msg_filters[position]; 
}

function getMFExclusive(position){
	if(position<0) return flase; 
	if(position>=msg_filters.length) return false; 
	return msg_filters_exclusive[position];
}

function setMFExclusive(position){
	if(position<0) return; 
	if(position>=msg_filters_exclusive.length) return; 
	msg_filters_exclusive[position] = true;
	document.getElementById("message_preview_mf_data").value=jsonMFData();
}

function resetMFExclusive(position){
	if(position<0) return; 
	if(position>=msg_filters_exclusive.length) return; 
	msg_filters_exclusive[position] = false;
	document.getElementById("message_preview_mf_data").value=jsonMFData();
}

function putMFExclusive(position, val){
	if(position<0) return; 
	if(position>=msg_filters_exclusive.length) return; 
	msg_filters_exclusive[position] = val;
}

function removeMF(position){
	if(getMF(position)==null) return; 
	var result_array = []; 
	var result_array_exclusive = []; 
	for(var i=0; i<msg_filters.length; i++)
		if(i!=position) {
			result_array[result_array.length] = msg_filters[i];
			result_array_exclusive[result_array_exclusive.length] = msg_filters_exclusive[i];
		}
	msg_filters = result_array;
	msg_filters_exclusive = result_array_exclusive;
	msg_filter_choosed = -1; 
	refreshMFList();
}

function escapeHTML(text){
	return text.replaceAll(/&/g, '&amp;')
	           .replaceAll(/</g, '&lt;')
	           .replaceAll(/>/g, '&gt;')
	           .replaceAll(/\"/g, '&quot;')
	           .replaceAll(/\'/g, '&apos;');
}

function chooseMF(index){
	var old_index = msg_filter_choosed;
	if(old_index!==-1) document.getElementById('mf_'+old_index).style.borderColor='black';
	if(msg_filter_choosed===index) msg_filter_choosed = -1; 
	else msg_filter_choosed = index; 
	if(index===msg_filter_choosed)
		document.getElementById('mf_'+index).style.borderColor='#DC143C';
	else{
		document.getElementById('mf_'+index).style.borderColor='black';
	}
}

function refreshMFList(){
	var target = document.getElementById('message_preview_mf_list'); 
	var html = '';
	var index = 0;
	for(var msg_filter of msg_filters)
		html+="<pre id='mf_"+index+"' style='border:1px solid "+((index===msg_filter_choosed)?"red":"black")+"; width: 500px; overflow:auto; display:inline-table' onclick='chooseMF("+ (index++) +")'>"+escapeHTML(msg_filter)+"</pre>&nbsp;<select><option id='mf_exclusive_"+(index-1)+"' onclick='setMFExclusive("+(index-1)+")'>\u041e\u0431\u0430\u0432\u0435\u0437\u043d\u043e</option><option id='mf_inclusive_"+(index-1)+"' onclick='resetMFExclusive("+(index-1)+")'>\u0423\u043a\u0459\u0443\u0447\u0438\u0432\u043e</option></select><br>";
	target.innerHTML = html;
	document.getElementById("message_preview_mf_data").value=jsonMFData();
	index = 0; 
	for(var msg_filter_ex of msg_filters_exclusive){
		if(!msg_filter_ex)
			document.getElementById("mf_inclusive_"+index).selected = "true"; 
		else
			document.getElementById("mf_exclusive_"+index).selected = "true"; 
		index++; 
	}
}

function jsonMFData(){
	return JSON.stringify({msg_filters: msg_filters, msg_filters_exclusive: msg_filters_exclusive}); 
}