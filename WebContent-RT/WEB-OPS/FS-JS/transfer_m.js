"UTF-8"

/**
 * Пребацивање података са поља на којима се уређују у форму.
 */


function escapeHTML(unsafe){
	 return unsafe
	 .replace(/&/g, "&amp;")
     .replace(/</g, "&lt;")
     .replace(/>/g, "&gt;")
     .replace(/"/g, "&quot;")
     .replace(/'/g, "&#039;")
}

function transport_data_old(field){
	var rt_place_ce   = document.getElementById('rt_place_ce_'+field);
	var rt_word_ce    = document.getElementById('rt_word_ce_'+field);
	var rt_number_ce  = document.getElementById('rt_number_ce_'+field);
	var rt_type_ce    = document.getElementById('rt_type_ce_'+field);
	var rt_subtype_ce = document.getElementById('rt_subtype_ce_'+field); 
	var rt_relation_ce  = document.getElementById('rt_relation_ce_'+field);
	var rt_outcome_ce   = document.getElementById('rt_outcome_ce_'+field);
	var rt_income_ce    = document.getElementById('rt_income_ce_'+field);
	var rt_direction_ce = document.getElementById('rt_direction_ce_'+field);
	var rt_destination_ce = document.getElementById('rt_destination_ce_'+field);
	var rt_notes_ce       = document.getElementById('rt_notes_ce_'+field);
	var rt_word_ce_old	  = document.getElementById('rt_word_ce_old_'+field);

	var rt_place_fm   = document.getElementById('rt_place_fm');
	var rt_word_fm    = document.getElementById('rt_word_fm');
	var rt_number_fm  = document.getElementById('rt_number_fm');
	var rt_type_fm    = document.getElementById('rt_type_fm');
	var rt_subtype_fm = document.getElementById('rt_subtype_fm'); 
	var rt_relation_fm  = document.getElementById('rt_relation_fm');
	var rt_outcome_fm   = document.getElementById('rt_outcome_fm');
	var rt_income_fm    = document.getElementById('rt_income_fm');
	var rt_direction_fm = document.getElementById('rt_direction_fm');
	var rt_destination_fm = document.getElementById('rt_destination_fm');
	var rt_notes_fm       = document.getElementById('rt_notes_fm');
	var rt_word_fm_old    = document.getElementById('rt_word_fm_old');
		
	rt_place_fm.value       = escapeHTML(rt_place_ce.innerText);
	rt_word_fm.value        = escapeHTML(rt_word_ce.innerText);
	rt_number_fm.value      = escapeHTML(rt_number_ce.innerText);
	rt_type_fm.value        = escapeHTML(rt_type_ce.innerText);
	rt_subtype_fm.value     = escapeHTML(rt_subtype_ce.innerText);
	rt_relation_fm.value    = escapeHTML(rt_relation_ce.innerText);
	rt_outcome_fm.value     = escapeHTML(rt_outcome_ce.innerText);
	rt_income_fm.value      = escapeHTML(rt_income_ce.innerText);
	rt_direction_fm.value   = escapeHTML(rt_direction_ce.innerText);
	rt_destination_fm.value = escapeHTML(rt_destination_ce.innerText);
	rt_notes_fm.value       = escapeHTML(rt_notes_ce.innerText);
	rt_word_fm_old.value    = escapeHTML(rt_word_ce_old.innerHTML);
}


function transport_data_neo(){
	var rt_place_ce   = document.getElementById('rt_place_ce');
	var rt_word_ce    = document.getElementById('rt_word_ce');
	var rt_number_ce  = document.getElementById('rt_number_ce');
	var rt_type_ce    = document.getElementById('rt_type_ce');
	var rt_subtype_ce = document.getElementById('rt_subtype_ce'); 
	var rt_relation_ce  = document.getElementById('rt_relation_ce');
	var rt_outcome_ce   = document.getElementById('rt_outcome_ce');
	var rt_income_ce    = document.getElementById('rt_income_ce');
	var rt_direction_ce = document.getElementById('rt_direction_ce');
	var rt_destination_ce = document.getElementById('rt_destination_ce');
	var rt_notes_ce       = document.getElementById('rt_notes_ce');

	var rt_place_fm   = document.getElementById('rt_place_fm');
	var rt_word_fm    = document.getElementById('rt_word_fm');
	var rt_number_fm  = document.getElementById('rt_number_fm');
	var rt_type_fm    = document.getElementById('rt_type_fm');
	var rt_subtype_fm = document.getElementById('rt_subtype_fm'); 
	var rt_relation_fm  = document.getElementById('rt_relation_fm');
	var rt_outcome_fm   = document.getElementById('rt_outcome_fm');
	var rt_income_fm    = document.getElementById('rt_income_fm');
	var rt_direction_fm = document.getElementById('rt_direction_fm');
	var rt_destination_fm = document.getElementById('rt_destination_fm');
	var rt_notes_fm       = document.getElementById('rt_notes_fm');
		
	rt_place_fm.value       = escapeHTML(rt_place_ce.innerText);
	rt_word_fm.value        = escapeHTML(rt_word_ce.innerText);
	rt_number_fm.value      = escapeHTML(rt_number_ce.innerText);
	rt_type_fm.value        = escapeHTML(rt_type_ce.innerText);
	rt_subtype_fm.value     = escapeHTML(rt_subtype_ce.innerText);
	rt_relation_fm.value    = escapeHTML(rt_relation_ce.innerText);
	rt_outcome_fm.value     = escapeHTML(rt_outcome_ce.innerText);
	rt_income_fm.value      = escapeHTML(rt_income_ce.innerText);
	rt_direction_fm.value   = escapeHTML(rt_direction_ce.innerText);
	rt_destination_fm.value = escapeHTML(rt_destination_ce.innerText);
	rt_notes_fm.value       = escapeHTML(rt_notes_ce.innerText);
}
