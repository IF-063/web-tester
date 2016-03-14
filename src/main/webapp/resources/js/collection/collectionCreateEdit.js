$(function() {

  $('.multipleSelect').select2({
    theme: 'bootstrap',
    width: '100%'
  });
  
  $('.multiSelect').multiSelect({
    dblClick: true
  });

//something wrong here  
//  $('.multiSelect').multiSelect({
//	  dblClick: true,
//	  selectableHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='type request name'>",
//	  selectionHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='type request name'>",
//	  afterInit: function(ms){
//	    var that = this,
//	        $selectableSearch = that.$selectableUl.prev(),
//	        $selectionSearch = that.$selectionUl.prev(),
//	        selectableSearchString = '#'+that.$container.attr('id')+' .ms-elem-selectable:not(.ms-selected)',
//	        selectionSearchString = '#'+that.$container.attr('id')+' .ms-elem-selection.ms-selected';
//
//	    that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
//	    .on('keydown', function(e){
//	      if (e.which === 40){
//	        that.$selectableUl.focus();
//	        return false;
//	      }
//	    });
//
//	    that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
//	    .on('keydown', function(e){
//	      if (e.which == 40){
//	        that.$selectionUl.focus();
//	        return false;
//	      }
//	    });
//	  },
//	  afterSelect: function(){
//	    this.qs1.cache();
//	    this.qs2.cache();
//	  },
//	  afterDeselect: function(){
//	    this.qs1.cache();
//	    this.qs2.cache();
//	  }
//	});

//need to add validation for unique name and requests  
  $('#requestCollection').validate({
    onkeyup: function(element) {
        $(element).valid();
    },
    messages: {
        name: {
            required: 'Name can not be empty',
        },
        description: {
            required: 'Description can not be empty'
        },
    },
    errorElement: "em",
    errorPlacement: function(error, element) {
        error.addClass("help-block");
        element.parents("div,td").addClass("has-feedback");
        error.insertAfter(element);
        if (!element.next("span")[0]) {
            $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
        }
    },
    success: function(label, element) {
        if (!$(element).next("span")[0]) {
            $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>").insertAfter($(element));
        }
    },
    highlight: function(element, errorClass, validClass) {
        $(element).parent('').addClass("has-error").removeClass("has-success");
        $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
    },
    unhighlight: function(element, errorClass, validClass) {
        $(element).parent('div,td').addClass("has-success").removeClass("has-error");
        $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
    },
  });  
  
  $('#select-all').click(function(){
      $('.multiSelect').multiSelect('select_all');
      return false;
  });
  $('#deselect-all').click(function(){
    $('.multiSelect').multiSelect('deselect_all');
    return false;
  });
  
  $('#reset').click(function() {
    location.reload();
    return false;
  });
  
  $('#clean').click(function(e) {
    $('.multipleSelect').select2('val', null);
    $('.multiSelect').multiSelect('deselect_all');
    $('input[type=text],textarea').val('');
    return false;
  });
})    
