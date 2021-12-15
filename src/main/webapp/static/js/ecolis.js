function confirmSuppressionTache(hrefParam){
	var result = confirm("Etes vous certain de vouloir supprimer Cette entite ?");
	if (result){
		window.location.href = hrefParam;
	}
	return result;
}