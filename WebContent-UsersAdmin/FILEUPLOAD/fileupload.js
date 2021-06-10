/**
 * Баратање са сликама, односно уносом слика. 
 */
function initDragAndDropFileChoose(dropContainerId, fileInputId){
	var dropContainerObj = document.getElementById(dropContainerId); 
	var fileInputObj = document.getElementById(fileInputId);
	dropContainerObj.ondragover = dropContainerObj.ondragenter =
		function (evt) {
		  evt.preventDefault();
		};
	dropContainerObj.ondrop = 
		function (evt) {
		  fileInputObj.files = evt.dataTransfer.files;
		  evt.preventDefault();
		  var res = "<table class='simpletable' style='width: 500px'><thead>"; 
		  res+="<th class='simpletable'>&#x41D;&#x430;&#x437;&#x438;&#x432;</th>";
		  res+="<th class='simpletable'>&#x41C;&#x418;&#x41C;&#x415; &#x422;&#x438;&#x43F;</th>";
		  res+="<th class='simpletable'>&#x412;&#x435;&#x43B;&#x438;&#x447;&#x438;&#x43D;&#x430;</th>";
		  dropContainerObj.innerHTML+="</thead>";
		  for (var i = 0; i < fileInputObj.files.length; i++) {
			  res+= "<tr class='simpletable'>";
			  res+="<td class='simpletable'>"+fileInputObj.files[i].name+"</td>";
			  res+="<td class='simpletable'>"+fileInputObj.files[i].type+"</td>";
			  res+="<td class='simpletable'>"+fileInputObj.files[i].size+"</td>";
			  res+= "</tr>";
		  }
		  res+= "</table>";
		  dropContainerObj.innerHTML=res;
	  };
}