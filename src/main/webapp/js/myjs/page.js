    $(function() {
        var $table = $("table");
        var currentPage = 1;
        var pageSize = 30;
        var sumRows = $table.find("tbody tr").length;
        var sumPages = Math.ceil(sumRows/pageSize);
        
        init();
        paging(currentPage)
        
        
        $("#prev").click(function(){
            currentPage--;
            init();
            paging(currentPage);
        })
        
        $("#next").click(function(){
            currentPage++;
            init();
            paging(currentPage);
        })
        
        var $page = $("<div class='page'></div>");
        for(var pageIndex=1;pageIndex<=sumPages;pageIndex++){
            $("<a href='#'><span>["+(pageIndex)+"]</span></a>").bind("click",{"newPage":pageIndex},function(event){
                currentPage=event.data["newPage"];//值得参考
                init();
                paging(currentPage);
            }).appendTo($page);
        }
        $page.insertAfter($table);
        
        function paging(currentPage){
            $table.find("tbody tr:not(.prevnext)").hide().slice((currentPage-1)*pageSize,(currentPage)*pageSize).show();
            $("#currentPage").val(currentPage+1);
            $("#currentPage").text(currentPage);
            $("#sumPages").text(sumPages);
        }
        
        function init(){
            if(currentPage==1){
                $("#prev").attr({"disabled":"disabled"});
            }else{
                $("#prev").removeAttr("disabled");
            }
            if(currentPage==sumPages){
                $("#next").attr({"disabled":"disabled"});
            }else{
                $("#next").removeAttr("disabled");
            }
        }
        
    })