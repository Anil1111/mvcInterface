$.fn.extend({
    majorChoose:function(data,attributes,names){
        attributes=attributes||null;
        function attrToString(o){
            if(typeof o!=='object'){return ''}
            var s='';
            $.each(o,function(i,e){
                s+=i+'=\"'+e+'\"'+' '
            });
            return $.trim(s)
        }
        var $select=$('<select '+attrToString(attributes[0])+'></select>'),$select2=$('<select '+attrToString(attributes[1])+'></select>');
        $.each(data,function(i,e){
            $select.append($('<option value="'+(e.name==='请选择'?'': e.name)+'" data-id="'+i+'">'+e.name+'</option>'))
        });
        $select.on('change',function(){
            var _id=$(this).find('option:selected').data('id');
            $select2.empty();
            if(data[_id]){
                $.each(data[_id]['data'],function(i,e){
                    $select2.append($('<option value="'+(e.name==='请选择'?'': e.name)+'" data-id="'+i+'">'+e.name+'</option>'))
                })
            }
        });
        var $s1=names&&names[0]&&$select.find('option:contains('+names[0]+')'),
            $s2=names&&names[1]&&$select.find('option:contains('+names[1]+')');
        $s1&&$s1.length>0?$s1.prop('selected',true):$select.find('option:eq(0)').prop('selected',true);
        $select.change();
        $s2&&$s2.length>0&&$s2.prop('selected',true);
        attributes&&attributes.length>0&&this.find('.'+$.trim(attributes[0]['class']).replace(/\s+/g,'.')).remove();
        attributes&&attributes.length>1&&this.find('.'+$.trim(attributes[1]['class']).replace(/\s+/g,'.')).remove();
        // $(this).find('.sel-box1').find('select').remove();
        // $(this).find('.sel-box2').find('select').remove();
        $(this).find('.sel-box1').append($select);
        $(this).find('.sel-box2').append($select2);
        // return [$(this).find('.sel-box1').find('select'),$(this).find('.sel-box2').find('select')];
    },
    languageChoose:function(data,attributes,names){
        attributes=attributes||null;
        function attrToString(o){
            if(typeof o!=='object'){return ''}
            var s='';
            $.each(o,function(i,e){
                s+=i+'=\"'+e+'\"'+' '
            });
            return $.trim(s)
        }
        var $select,$select2,isJq=false;
        if(attributes[0]&&attributes[0] instanceof jQuery&&attributes[0].is('select')){
            $select=attributes[0];
            isJq=true;
        }else{$select=$('<select '+attrToString(attributes[0])+'></select>');}
        if(attributes[1]&&attributes[1] instanceof jQuery&&attributes[1].is('select')){
            $select2=attributes[1]
        }else{$select2=$('<select '+attrToString(attributes[1])+'></select>');}

        $select.empty();
        $select2.empty();
        $.each(data,function(i,e){
            $select.append($('<option value="'+(e.name==='请选择'?'': e.name)+'" data-id="'+i+'">'+e.name+'</option>'))
        });
        $select.on('change',function(){
            var _id=$(this).find('option:selected').data('id');
            $select2.empty();
            if(data[_id]){
                $.each(data[_id]['data'],function(i,e){
                    $select2.append($('<option value="'+(e.name==='请选择'?'': e.name)+'" data-id="'+i+'">'+e.name+'</option>'))
                });
                if(Object.keys(data[_id]['data']).length<=1){
                    $select2.addClass('can_empty').prev().find('.must_star').css('visibility','hidden');
                }else{
                    $select2.removeClass('can_empty').prev().find('.must_star').css('visibility','visible');
                }
            }

        });
        var $s1=names&&names[0]&&$select.find('option:contains('+names[0]+')'),
            $s2=names&&names[1]&&$select.find('option:contains('+names[1]+')');
        $s1&&$s1.length>0?$s1.prop('selected',true):$select.find('option:eq(0)').prop('selected',true);
        $select.change();
        $s2&&$s2.length>0&&$s2.prop('selected',true);
        if(!isJq){
            attributes&&attributes.length>0&&this.find('.'+$.trim(attributes[0]['class']).replace(/\s+/g,'.')).remove();
            attributes&&attributes.length>1&&this.find('.'+$.trim(attributes[1]['class']).replace(/\s+/g,'.')).remove();
            $(this).append($select,$select2)
        }
    }
});