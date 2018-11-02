/**
 * Created by Jason on 2018/1/10.
 */
$(function () {
    var cancelFlag = true;
    var postUrl = '';
    if (isFirstResume) {
        postUrl = '/new/resume/info'
    }
    else {
        postUrl = '/trainee/resume/modify'
    }
    //右侧简历模块 点击添加模块
    $moduleList.on('click', 'li', function () {
        var li = $(this), complete = li.find('.complete');
        function moduleSwitch(moduleName, data, el, cont) {
            if (resuuid) {
                if (complete.is(':hidden')) {
                    $.post('/trainee/resumemod/check', {
                        ruuid: resuuid,
                        mod: moduleName,
                        cont: data
                    }, function (d) {
                        d = JSON.parse(d);
                        if (d.code == 100) {
                            complete.toggle();
                            if (el) {
                                (function showModule(el) {
                                    if (el.find('.preview').is(':hidden')) {
                                        el.find('.module-name').find('.edit_btn').show();
                                        el.find('.module-name').show().siblings('.edit').hide();
                                        // el.find('.module-name').show().siblings('.preview,.edit').hide();
                                    }
                                    el.slideToggle(300);
                                })(el);
                            }
                        } else {
                            popup1('添加模块失败！');
                        }
                    })
                } else {
                    $.post('/trainee/resumemod/uncheck', {
                        ruuid: resuuid,
                        mod: moduleName,
                        cont: cont
                    }, function (d) {
                        d = JSON.parse(d);
                        if (d.code == 100) {
                            complete.toggle();
                            if (el) {
                                (function showModule(el) {
                                    if (el.find('.preview').is(':hidden')) {
                                        el.find('.module-name').find('.edit_btn').show();
                                        el.find('.module-name').show().siblings('.edit').hide();
                                        // el.find('.module-name').show().siblings('.preview,.edit').hide();
                                    }
                                    el.slideToggle(300);
                                })(el);
                            }
                        } else {
                            popup1('删除模块失败！');
                        }
                    })
                }
            } else {
                complete.toggle();
                if (el) {
                    (function showModule(el) {
                        if (el.find('.preview').is(':hidden')) {
                            el.find('.module-name').find('.edit_btn').show();
                            el.find('.module-name').show().siblings('.edit').hide();
                        }
                        el.slideToggle(300);
                    })(el);
                }
            }
        }

        switch (li[0]) {
            case $('.module1')[0]:
                scroll($('html,body'));
                li.find('.complete').show();
                break;
            case $('.module2')[0]:
                scroll($expectPractice);
                li.find('.complete').show();
                break;
            case $('.module3')[0]:
                scroll($eduBg);
                li.find('.complete').show();
                break;
            case $('.module4')[0]:
                moduleSwitch('prac', dataPracticeEx, $practiceEx, '[]');
                break;
            case $('.module5')[0]:
                moduleSwitch('proj', dataAcademicEx, $academicEx, '[]');
                break;
            case $('.module6')[0]:
                moduleSwitch('expe', dataSchoolEx, $schoolEx, '[]');
                break;
            case $('.module7')[0]:
                moduleSwitch('skill', dataSkillHobby, $skillHobby, '[]');
                break;
            case $('.module8')[0]:
                moduleSwitch('prod', dataOpus, $opus, '[]');
                break;
            case $('.module9')[0]:
                moduleSwitch('desc', dataPersonalEv, $personalEv, '');
                break;
            case $('.module10')[0]:
                moduleSwitch('atta_info', dataAtta, null, '[]');
                $attachment.slideToggle(300);
                break;
        }
    })
    $('.switch').on('change', function () {
        var stype = $(this).attr('data-stype'),
            guuid = $(this).attr('data-guuid');
        var resume_type = stype == 'online' ? 'onresume' : 'attresume', $this = $(this);
        if ($(this).val() == 'chinese') {
            //英文简历编辑页面，跳转到中文简历
            window.location = '/trainee/' + resume_type + '/modify/' + $(this).attr('data-cruuid');
            //location.reload();
        } else {
            //中文简历编辑页面，英文简历可能有可能没有，没有就创建
            if ($(this).attr('data-eruuid')) {
                window.location = '/trainee/' + resume_type + '/modify/' + $(this).attr('data-eruuid');
                //location.reload();
            }
            else {
                $.post('/trainee/resume/create', {
                    stype: stype,
                    guuid: guuid,
                    lan: 'english',
                    sdtoken: sdtoken
                }, function (d) {
                    if (d.code == 100) {
                        ruuid = d.msg.ruuid;
                        location.href = '/trainee/' + resume_type + '/add?guuid=' + guuid + "&ruuid=" + ruuid;
                        //location.reload();
                    } else {
                        layer.alert(d.msg);
                        $.get('/getsdtoken?' + Math.random(), function (d) {
                            sdtoken = d;
                        });
                    }
                }, 'json')
            }
        }
    });
    //简历名称修改
    $('.resume-name .revise').on('click', function () {
        //判断是否有模块处于编辑状态
        isedit();
        if (!isEdit) {
            $(this).parent().hide().next().show().find('input').val($('.name-text').text()).focus();
        }
    })
    $('.rename .confirm').on('click', function () {
        var val = $(this).siblings('input').val().trim();
        if (!val || /^\s+$/.test(val)) {
            popup1('简历名称不能为空！');
            $(this).siblings('input').val('').focus();
            return;
        } else if (!/^[^`~!@#$%^&*()=|\\\][\]\{\}:;'\,.<>/?]{1}[^`~!@$%^&()=|\\\][\]\{\}:;'\,.<>?]{0,39}$/.test(val)) {
            $('.cover').fadeIn(300).children('.popup2').fadeIn(300).find('.tips').html('您的输入不符合规范<br>简历名称中只允许"_","-","/"等特殊字符！');
            return;
        }
        $(this).parent().hide().prev().show().find('.name-text').text(val);
        $.post(postUrl, {
            guuid: groupuuid,
            ruuid: resuuid,
            stype: stype,
            sdtoken: sdtoken,
            params: JSON.stringify({'mod': 'rnme', 'cont': JSON.stringify({'name': val})})
        }, function (d) {
            d = JSON.parse(d);
            if (d.code == 100) {
                resuuid = d.msg.ruuid;
                resumeIntegrity(d.msg.score, d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
            } else {
                popup1('修改失败！');
            }
            $.get('/getsdtoken?' + Math.random(), function (d) {
                sdtoken = d;
            });
        });
    })

    //开启关闭邀约
    $('.open-invite').on('click', function () {
        var $this = $(this), text = $this.find('.open-btn').text();
        if (text === '开启邀约') {
            $('.cover').fadeIn(300).find('.popup5').fadeIn(300);
            $('.popup5 .open').on('click', function () {
                $.get('/my/invite/open', {}, function (d) {
                    d = JSON.parse(d);
                    if (d.code == 100) {
                        $('.cover').fadeOut(300).find('.popup5').fadeOut(300);
                        $this.find('.open-btn').text('关闭邀约');
                    }
                    else {
                        popup1('成功开启邀约！')
                    }
                });

            })
        } else {
            $('.cover').fadeIn(300).find('.popup2').fadeIn(300).find('.tips').text('确定关闭邀约吗？');
            $('.popup2').on('click', '.yes', function () {
                $.get('/my/invite/close', {}, function (d) {
                    d = JSON.parse(d);
                    if (d.code == 100) {
                        $this.find('.open-btn').text('开启邀约');
                    } else {
                        popup1('关闭邀约成功！')
                    }
                });

            })
        }
    })

    //模块点击保存
    //各模块数据
    var dataPracticeEx = JSON.stringify(practice_info), dataAcademicEx = JSON.stringify(project_info),
        dataSchoolEx = JSON.stringify(exp_info), dataSkillHobby = JSON.stringify(skill_info),
        dataOpus = JSON.stringify(product_info),
        dataPersonalEv = description, dataAtta = JSON.stringify(atta_info);
    $('.btn-box .confirm').on('click', function () {
        var $this = $(this), data = {info: []}, info = '';
        $.each($this.parent().siblings('.content-box'), function (i, v) {
            info = $(this).find('form').serializeArray();
            data.info.push(info);
        })
        var module = $this.parents('.module-item')[0], html;
        //判断信息是否填写完整
        var flag1 = true;
        if (module !== $eduBg[0] && module !== $skillHobby[0]) {
            $.each(data.info, function (index, value) {
                $.each(value, function (i, v) {
                    if (!v.value || /^\s+$/.test(v.value)) {
                        popup1('请将信息填写完整！');
                        flag1 = false;
                        return flag1;
                    }
                })
                return flag1;
            })
        }
        //信息正确完整执行
        if (flag1) {
            var params = [], flag2 = true;
            //显示预览页面,判断当前在哪个模块，就用哪个模板
            switch (module) {
                case $basicInfo[0]:
                    if (!/^[0-9\+\-\s]{11,15}$/.test(data.info[0][6].value)) {
                        popup1('手机号码错误！');
                        return;
                    }
                    if (!/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(data.info[0][7].value)) {
                        popup1('邮箱格式错误！');
                        return;
                    }
                    var gender = 1;
                    if (info[1].value == '女' || info[1].value == 'Female') {
                        gender = 2;
                    } else if (info[1].value == '保密' || info[1].value == 'Keep secrect') {
                        gender = 0;
                    }
                    $.post(postUrl, {
                            guuid: groupuuid,
                            ruuid: resuuid,
                            stype: stype,
                            sdtoken: sdtoken,
                            params: JSON.stringify({
                                'mod': 'newbase',
                                'cont': JSON.stringify({
                                    'headpic': info[8].value,
                                    'rname': info[0].value,
                                    'gender': gender,
                                    'birthy': info[2].value,
                                    'birthm': parseInt(info[3].value) + '',
                                    'country': info[4].value,
                                    'bcity': info[5].value,
                                    'telep': info[6].value,
                                    'email': info[7].value
                                })
                            })
                        },
                        function (d) {
                            d = JSON.parse(d);
                            if (d.code == 100) {
                                resuuid = d.msg.ruuid;
                                baseinfo = true;
                                isdefault = d.msg.isdefault;
                                resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                giftbag(d.msg.score, isdefault, baseinfo, expect_job, education);
                                $('.return').attr('href', '/my');
                                $(".download").attr("data-att", '1');
                                $(".download").attr("data-href", '/trainee/resume/down/'+resuuid+'/'+languageType);
                                $(".operation .preview").attr("data-pre", '1');
                                $(".operation .preview").attr("data-href", '/trainee/resume/view/' + resuuid);
                            } else {
                                popup1('保存失败！');
                            }
                            $.get('/getsdtoken?' + Math.random(), function (d) {
                                sdtoken = d;
                            });
                        });
                    html = template('tpl1-1', data);
                    $basicInfo.find('.cancel').show();
                    break;
                case $expectPractice[0]:
                    var infoL = info.length,
                        sY = info[infoL - 6].value,
                        sM = info[infoL - 5].value,
                        eY = info[infoL - 4].value,
                        eM = info[infoL - 3].value;
                    if (parseInt(sY) > parseInt(eY)) {
                        popup1('开始时间不能晚于结束时间！');
                        return;
                    }
                    if (parseInt(sY) === parseInt(eY)) {
                        if (parseInt(sM) > parseInt(eM)) {
                            popup1('开始时间不能晚于结束时间！');
                            return;
                        }
                    }
                    // if (parseInt(sY) === nowtime.y) {
                    //     if (parseInt(sM) < nowtime.m) {
                    //         popup1('实习月份不能早于当前时间哦！');
                    //         return;
                    //     }
                    // }
                    if (!/^\d{1,}$/.test(data.info[0][data.info[0].length - 2].value)) {
                        popup1('期望日薪只能输入数字');
                        return;
                    }
                    var $post = $expectPractice.find('.edit .post'),
                        $area = $expectPractice.find('.edit .area'),
                        $postL = $post.length,
                        $areaL = $area.length;
                    var str1 = '', str2 = '';
                    $.each($post, function (i, v) {
                        str1 += data.info[0][i].value + ',';
                    })
                    $.each($area, function (i, v) {
                        str2 += data.info[0][$postL + i].value + ',';
                    })
                    data.info[0][0].value = str1.substr(0, str1.length - 1);
                    data.info[0][$postL].value = str2.substr(0, str2.length - 1);
                    data.info[0].push({value: $postL});
                    data.info[0].push({value: $areaL});
                    $.post(postUrl, {
                            guuid: groupuuid,
                            ruuid: resuuid,
                            stype: stype,
                            sdtoken: sdtoken,
                            params: JSON.stringify({
                                'mod': 'expc',
                                'cont': JSON.stringify({
                                    'job': info[0].value,
                                    'cty': info[$postL].value,
                                    'day': info[infoL - 7].value,
                                    'begin_year': info[infoL - 6].value,
                                    'begin_month': info[infoL - 5].value,
                                    'end_year': info[infoL - 4].value,
                                    'end_month': info[infoL - 3].value,
                                    'sly': info[infoL - 2].value,
                                    'repy': info[infoL - 1].value.split('-')[0],
                                    'repm': info[infoL - 1].value.split('-')[1],
                                    'repd': info[infoL - 1].value.split('-')[2]
                                })
                            })
                        },
                        function (d) {
                            d = JSON.parse(d);
                            if (d.code == 100) {
                                resuuid = d.msg.ruuid;
                                expect_job = true;
                                isdefault = d.msg.isdefault;
                                reportWay = info[infoL - 1].value;
                                resumeIntegrity(d.msg.score, d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                giftbag(d.msg.score, isdefault, baseinfo, expect_job, education);
                                $('.return').attr('href', '/my');
                            } else {
                                popup1('保存失败！');
                            }
                            $.get('/getsdtoken?' + Math.random(), function (d) {
                                sdtoken = d;
                            });
                        });
                    html = template('tpl2-1', data);
                    break;
                case $eduBg[0]:
                    //判断必填项有没有填写完整
                    $.each(data.info, function (index, value) {
                        $.each(value, function (i, v) {
                            if (!v.value || /^\s+$/.test(v.value)) {
                                if (languageType == 'chinese') {
                                    if ((i !== 5) && (i !== 7) && (i !== 8) && (i !== 9)) {
                                        popup1('请将必填项信息填写完整！');
                                        flag2 = false;
                                        return flag2;
                                    }
                                } else {
                                    if ((i !== 5) && (i !== 6) && (i !== 7)) {
                                        popup1('请将必填项信息填写完整！');
                                        flag2 = false;
                                        return flag2;
                                    }
                                }
                            }
                            if (languageType == 'chinese') {
                                if (value[7].value === '请选择') {
                                    value[7].value = '';
                                }
                            }
                        })
                        return flag2;
                    })
                    //判断时间
                    $.each(data.info, function (i, v) {
                        if (parseInt(v[1].value) > parseInt(v[2].value)) {
                            popup1('开始时间不能大于结束时间！');
                            flag2 = false;
                        }
                        return flag2;
                    })
                    if (flag2) {
                        $.each($eduBg.find('.isabroad'), function (i, v) {
                            if ($(v).hasClass('active')) {
                                var isoversea = languageType == 'chinese' ? '（海外）' : '（Oversea）';
                                data.info[i].push({value: isoversea});
                            }
                        })
                        if (languageType == 'chinese') {
                            $.each(data.info, function (i, v) {
                                var item = {
                                    'school': v[0].value,
                                    'oversea': v[10] ? true : false,
                                    'begin_year': v[1].value,
                                    'end_year': v[2].value,
                                    'degree': v[3].value,
                                    'major_class': v[5].value,
                                    'major_subject': v[4].value,
                                    'major': v[6].value,
                                    'ranking': v[7].value,
                                    'course': v[8].value,
                                    'honor': v[9].value,
                                    'city': '全国'
                                };
                                params.push(item);
                            })
                        } else {
                            $.each(data.info, function (i, v) {
                                var item = {
                                    'school': v[0].value,
                                    'oversea': v[8] ? true : false,
                                    'begin_year': v[1].value,
                                    'end_year': v[2].value,
                                    'degree': v[3].value,
                                    'major': v[4].value,
                                    'GPA': v[5].value,
                                    'course': v[6].value,
                                    'honor': v[7].value,
                                    'city': 'National'
                                }
                                params.push(item);
                            })
                        }
                        $.post(postUrl, {
                                guuid: groupuuid,
                                ruuid: resuuid,
                                stype: stype,
                                sdtoken: sdtoken,
                                params: JSON.stringify({
                                    'mod': 'educ',
                                    'cont': JSON.stringify(params)
                                })
                            },
                            function (d) {
                                d = JSON.parse(d);
                                if (d.code == 100) {
                                    resuuid = d.msg.ruuid;
                                    education = true;
                                    isdefault = d.msg.isdefault;
                                    resumeIntegrity(d.msg.score, d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                    giftbag(d.msg.score, isdefault, baseinfo, expect_job, education);
                                    $('.return').attr('href', '/my');
                                } else {
                                    popup1('保存失败！');
                                }
                                $.get('/getsdtoken?' + Math.random(), function (d) {
                                    sdtoken = d;
                                });
                            });
                        html = template('tpl3-1', data);
                    } else {
                        return;
                    }
                    break;
                case $practiceEx[0]:
                    $.each(data.info, function (i, v) {
                        var sY = v[2].value, sM = v[3].value, eY = v[4].value, eM = v[5].value;
                        if (parseInt(sY) > parseInt(eY)) {
                            popup1('开始时间不能晚于结束时间！');
                            flag2 = false;
                            return false;
                        }
                        if (parseInt(sY) === parseInt(eY)) {
                            if (parseInt(sM) > parseInt(eM)) {
                                popup1('开始时间不能晚于结束时间！');
                                flag2 = false;
                                return false;
                            }
                        }
                        if (parseInt(sY) > nowtime.y) {
                            popup1('开始时间不能晚于当前时间哦！');
                            flag2 = false;
                            return false;
                        }
                        if (parseInt(sY) === nowtime.y) {
                            if (parseInt(sM) > nowtime.m) {
                                popup1('开始时间不能晚于当前时间哦！');
                                flag2 = false;
                                return false;
                            }
                        }
                    })
                    if (flag2) {
                        $.each(data.info, function (i, v) {
                            var item = {
                                "name": v[0].value,
                                "play_role": v[1].value,
                                "begin_year": v[2].value,
                                "begin_month": parseInt(v[3].value) + '',
                                "end_year": v[4].value == '至今' || v[4].value == 'Present' ? '0' : v[4].value,
                                "end_month": v[5].value == '至今' || v[5].value == 'Present' ? '0' : parseInt(v[5].value) + '',
                                "city": v[6].value,
                                "description": v[7].value
                            };
                            params.push(item);
                        })
                        $.post(postUrl, {
                                guuid: groupuuid,
                                ruuid: resuuid,
                                stype: stype,
                                sdtoken: sdtoken,
                                params: JSON.stringify({
                                    'mod': 'prac',
                                    'cont': JSON.stringify(params)
                                })
                            },
                            function (d) {
                                d = JSON.parse(d);
                                if (d.code == 100) {
                                    resuuid = d.msg.ruuid;
                                    isdefault = d.msg.isdefault;
                                    resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                    $('.return').attr('href', '/my');
                                    dataPracticeEx = JSON.stringify(params);
                                } else {
                                    popup1('保存失败！');
                                }
                                $.get('/getsdtoken?' + Math.random(), function (d) {
                                    sdtoken = d;
                                });
                            });
                        html = template('tpl4-1', data);
                    } else {
                        return;
                    }
                    break;
                case $academicEx[0]:
                    $.each(data.info, function (i, v) {
                        var sY = v[2].value, sM = v[3].value, eY = v[4].value, eM = v[5].value;
                        if (parseInt(sY) > parseInt(eY)) {
                            popup1('开始时间不能晚于结束时间！');
                            flag2 = false;
                            return false;
                        }
                        if (parseInt(sY) === parseInt(eY)) {
                            if (parseInt(sM) > parseInt(eM)) {
                                popup1('开始时间不能晚于结束时间！');
                                flag2 = false;
                                return false;
                            }
                        }
                        if (parseInt(sY) > nowtime.y) {
                            popup1('开始时间不能晚于当前时间哦！');
                            flag2 = false;
                            return false;
                        }
                        if (parseInt(sY) === nowtime.y) {
                            if (parseInt(sM) > nowtime.m) {
                                popup1('开始时间不能晚于当前时间哦！');
                                flag2 = false;
                                return false;
                            }
                        }
                    })
                    if (flag2) {
                        $.each(data.info, function (i, v) {
                            var item = {
                                "name": v[0].value,
                                "play_role": v[1].value,
                                "begin_year": v[2].value,
                                "begin_month": parseInt(v[3].value) + '',
                                "end_year": v[4].value == '至今' || v[4].value == 'Present' ? '0' : v[4].value,
                                "end_month": v[5].value == '至今' || v[5].value == 'Present' ? '0' : parseInt(v[5].value) + '',
                                "city": v[6].value,
                                "description": v[7].value
                            };
                            params.push(item);
                        })
                        $.post(postUrl, {
                                guuid: groupuuid,
                                ruuid: resuuid,
                                stype: stype,
                                sdtoken: sdtoken,
                                params: JSON.stringify({
                                    'mod': 'proj',
                                    'cont': JSON.stringify(params)
                                })
                            },
                            function (d) {
                                d = JSON.parse(d);
                                if (d.code == 100) {
                                    resuuid = d.msg.ruuid;
                                    resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                    $('.return').attr('href', '/my');
                                    dataAcademicEx = JSON.stringify(params);
                                } else {
                                    popup1('保存失败！');
                                }
                                $.get('/getsdtoken?' + Math.random(), function (d) {
                                    sdtoken = d;
                                });
                            });
                        html = template('tpl5-1', data);
                    } else {
                        return;
                    }
                    break;
                case $schoolEx[0]:
                    $.each(data.info, function (i, v) {
                        var sY = v[2].value, sM = v[3].value, eY = v[4].value, eM = v[5].value;
                        if (parseInt(sY) > parseInt(eY)) {
                            popup1('开始时间不能晚于结束时间！');
                            flag2 = false;
                            return false;
                        }
                        if (parseInt(sY) === parseInt(eY)) {
                            if (parseInt(sM) > parseInt(eM)) {
                                popup1('开始时间不能晚于结束时间！');
                                flag2 = false;
                                return false;
                            }
                        }
                        if (parseInt(sY) > nowtime.y) {
                            popup1('开始时间不能晚于当前时间哦！');
                            flag2 = false;
                            return false;
                        }
                        if (parseInt(sY) === nowtime.y) {
                            if (parseInt(sM) > nowtime.m) {
                                popup1('开始时间不能晚于当前时间哦！');
                                flag2 = false;
                                return false;
                            }
                        }
                    })
                    if (flag2) {
                        $.each(data.info, function (i, v) {
                            var item = {
                                "name": v[0].value,
                                "play_role": v[1].value,
                                "begin_year": v[2].value,
                                "begin_month": parseInt(v[3].value) + '',
                                "end_year": v[4].value == '至今' || v[4].value == 'Present' ? '0' : v[4].value,
                                "end_month": v[5].value == '至今' || v[5].value == 'Present' ? '0' : parseInt(v[5].value) + '',
                                "city": v[6].value,
                                "description": v[7].value
                            };
                            params.push(item);
                        })
                        $.post(postUrl, {
                                guuid: groupuuid,
                                ruuid: resuuid,
                                stype: stype,
                                sdtoken: sdtoken,
                                params: JSON.stringify({
                                    'mod': 'expe',
                                    'cont': JSON.stringify(params)
                                })
                            },
                            function (d) {
                                d = JSON.parse(d);
                                if (d.code == 100) {
                                    resuuid = d.msg.ruuid;
                                    isdefault = d.msg.isdefault;
                                    exp_info = params;
                                    resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                    $('.return').attr('href', '/my');
                                    dataSchoolEx = JSON.stringify(params);
                                } else {
                                    popup1('保存失败！');
                                }
                                $.get('/getsdtoken?' + Math.random(), function (d) {
                                    sdtoken = d;
                                });
                            });
                        html = template('tpl6-1', data);
                    } else {
                        return;
                    }
                    break;
                case $skillHobby[0]:
                    //判断必填项有没有填写完整
                    $.each(data.info, function (index, value) {
                        $.each(value, function (i, v) {
                            if (!v.value || /^\s+$/.test(v.value)) {
                                if (value[0].value == '语言水平') {
                                    if (v.name === 'score') {
                                        popup1('请正确填写分数！');
                                        flag2 = false;
                                        return flag2;
                                    }
                                    if (v.name != 'language_level') {
                                        popup1('请将必填项信息填写完整！');
                                        flag2 = false;
                                        return flag2;
                                    }
                                } else {
                                    popup1('请将必填项信息填写完整！');
                                    flag2 = false;
                                    return flag2;
                                }
                            }
                            if (value[0].value == '语言水平' && v.name === 'score') {
                                if (v.value.length > 4) {
                                    popup1('请正确填写分数！');
                                    flag2 = false;
                                    return flag2;
                                }
                            }
                        })
                        return flag2;
                    })
                    if (flag2) {
                        $.each(data.info, function (i, v) {
                            var params1 = [], item = {};
                            if (v[0].value === '语言水平') {
                                for (var j = 0; j < (v.length - 1) / 3; j++) {
                                    item = {
                                        "language_type": v[j * 3 + 1].value,
                                        "language_level": v[j * 3 + 2].value,
                                        "language_score": v[j * 3 + 3].value
                                    };
                                    params1.push(item);
                                }
                                params.push({"info": params1, "type": "语言水平"});
                            } else {
                                item = {
                                    "info": v[1].value,
                                    "type": v[0].value
                                }
                                params.push(item);
                            }
                        });
                        $.post(postUrl, {
                                guuid: groupuuid,
                                ruuid: resuuid,
                                stype: stype,
                                sdtoken: sdtoken,
                                params: JSON.stringify({
                                    'mod': 'skill',
                                    'cont': JSON.stringify(params)
                                })
                            },
                            function (d) {
                                d = JSON.parse(d);
                                if (d.code == 100) {
                                    resuuid = d.msg.ruuid;
                                    isdefault = d.msg.isdefault;
                                    skill_info = params;
                                    resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                    $('.return').attr('href', '/my');
                                    dataSkillHobby = JSON.stringify(params);
                                } else {
                                    popup1('保存失败！');
                                }
                                $.get('/getsdtoken?' + Math.random(), function (d) {
                                    sdtoken = d;
                                });
                            });
                        $.each(data.info, function (i, v) {
                            if (v[0].value === '语言水平') {
                                var str = '';
                                $.each(v, function (index, value) {
                                    str += ',' + value.value;
                                })
                                var arr = str.split(','), arr2 = arr.splice(2, arr.length), str1 = '';
                                for (var a = 1; a <= arr2.length / 3; a++) {
                                    arr2[3 * a - 1] = '(' + arr2[3 * a - 1] + ')';
                                    str1 += '<span>' + arr2[3 * a - 3] + '&nbsp;' + arr2[3 * a - 2] + '&nbsp;' + arr2[3 * a - 1] + '</span><br>';
                                }
                                v[1].value = str1;
                            } else {
                                v[1].value = '<span>' + v[1].value + '</span>';
                            }
                        })
                        html = template('tpl7-1', data);
                    } else {
                        return;
                    }
                    break;
                case $opus[0]:
                    $.each(data.info, function (i, v) {
                        var item = {
                            "url": v[0].value,
                            "info": v[1].value
                        };
                        params.push(item);
                    });
                    $.post(postUrl, {
                            guuid: groupuuid,
                            ruuid: resuuid,
                            stype: stype,
                            sdtoken: sdtoken,
                            params: JSON.stringify({
                                'mod': 'prod',
                                'cont': JSON.stringify(params)
                            })
                        },
                        function (d) {
                            d = JSON.parse(d);
                            if (d.code == 100) {
                                resuuid = d.msg.ruuid;
                                isdefault = d.msg.isdefault;
                                resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                $('.return').attr('href', '/my');
                                dataOpus = JSON.stringify(params);
                            } else {
                                popup1('保存失败！');
                            }
                            $.get('/getsdtoken?' + Math.random(), function (d) {
                                sdtoken = d;
                            });
                        });
                    html = template('tpl8-1', data);
                    break;
                case $personalEv[0]:
                    $.post(postUrl, {
                            guuid: groupuuid,
                            ruuid: resuuid,
                            stype: stype,
                            sdtoken: sdtoken,
                            params: JSON.stringify({
                                'mod': 'desc',
                                'cont': JSON.stringify({'judge': info[0].value})
                            })
                        },
                        function (d) {
                            d = JSON.parse(d);
                            if (d.code == 100) {
                                resuuid = d.msg.ruuid;
                                resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                $('.return').attr('href', '/my');
                                dataPersonalEv = JSON.stringify({'judge': info[0].value});
                            } else {
                                popup1('保存失败！');
                            }
                            $.get('/getsdtoken?' + Math.random(), function (d) {
                                sdtoken = d;
                            });
                        });
                    html = template('tpl9-1', data);
                    break;
            }
            var $edit = $this.parents('.edit'), $preview = $edit.siblings('.preview');
            $preview.html(html);
            if (module == $basicInfo[0]) {
                $preview.find('.head img').attr('src', $('#img_upload').attr('src'));
            }
            if (module == $eduBg[0]) {
                //隐藏内容为空的条目的title
                hideTitle('.score');
                hideTitle('.course');
                hideTitle('.honor');
            }
            $(module).find('.edit_btn').show();
            $edit.hide();
            $preview.show();
            scroll($(module));
        }
    })
    //模块点击取消
    $('.btn-box .cancel').on('click', function () {
        var $this = $(this);
        $('.cover').fadeIn(200).find('.popup2').fadeIn(200).find('.tips').html('<span>确定取消编辑吗？</span><br><span style="display: block;color: #adadad;font-size: 14px;margin-top: 10px">已填写信息将不会保存</span>');
        $('.yes').off('click').on('click', function () {
            if (cancelFlag) {
                $this.parents('.edit').hide().siblings('.preview').show().siblings('.module-name').find('.edit_btn').show();
            } else {
                $this.parents('.edit').hide().siblings('.module-name').find('.edit_btn').show();
            }
        })
    })
    //删除该条
    $('.edit').on('click', '.delete', function () {
        var $this = $(this), module = $this.parents('.module-item');
        //显示增加一条按钮
        $this.parent('.content-box').siblings('.add').show();
        var l = module.find('.content-box').length;
        //判断信息条数，切换教育背景第一条删除按钮的显示隐藏
        if (module[0] === $eduBg[0]) {
            if (l - 1 === 1) {
                $this.parents('.edit').find('.delete').hide();
            }
            $this.parent().remove();
        } else {
            //判断信息条数，切换对应模块显示隐藏
            if (l === 1) {
                $('.cover').fadeIn(300).find('.popup6').fadeIn(300);
                $('.yes6').off('click').on('click', function () {
                    $('.cover').fadeOut(300).find('.popup6').fadeOut(300);
                    clear(module.find('.edit'));
                    module.find('.edit').slideUp(300);
                    module.find('.module-name').slideDown(300).find('.edit_btn').show();
                    switch (module[0]) {
                        case $practiceEx[0]:
                            $.post(postUrl, {
                                    guuid: groupuuid,
                                    ruuid: resuuid,
                                    stype: stype,
                                    sdtoken: sdtoken,
                                    params: JSON.stringify({
                                        'mod': 'prac',
                                        'cont': '[]'
                                    })
                                },
                                function (d) {
                                    d = JSON.parse(d);
                                    if (d.code == 100) {
                                        resuuid = d.msg.ruuid;
                                        resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                        $('.return').attr('href', '/my');
                                        dataPracticeEx = '';
                                    } else {
                                        popup1('删除失败！');
                                    }
                                    $.get('/getsdtoken?' + Math.random(), function (d) {
                                        sdtoken = d;
                                    });
                                });
                            break;
                        case $academicEx[0]:
                            $.post(postUrl, {
                                    guuid: groupuuid,
                                    ruuid: resuuid,
                                    stype: stype,
                                    sdtoken: sdtoken,
                                    params: JSON.stringify({
                                        'mod': 'proj',
                                        'cont': '[]'
                                    })
                                },
                                function (d) {
                                    d = JSON.parse(d);
                                    if (d.code == 100) {
                                        resuuid = d.msg.ruuid;
                                        resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                        $('.return').attr('href', '/my');
                                        dataAcademicEx = '';
                                    } else {
                                        popup1('删除失败！');
                                    }
                                    $.get('/getsdtoken?' + Math.random(), function (d) {
                                        sdtoken = d;
                                    });
                                });
                            break;
                        case $schoolEx[0]:
                            $.post(postUrl, {
                                    guuid: groupuuid,
                                    ruuid: resuuid,
                                    stype: stype,
                                    sdtoken: sdtoken,
                                    params: JSON.stringify({
                                        'mod': 'expe',
                                        'cont': '[]'
                                    })
                                },
                                function (d) {
                                    d = JSON.parse(d);
                                    if (d.code == 100) {
                                        resuuid = d.msg.ruuid;
                                        resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                        $('.return').attr('href', '/my');
                                        dataSchoolEx = '';
                                    } else {
                                        popup1('删除失败！');
                                    }
                                    $.get('/getsdtoken?' + Math.random(), function (d) {
                                        sdtoken = d;
                                    });
                                });
                            break;
                        case $skillHobby[0]:
                            $.post(postUrl, {
                                    guuid: groupuuid,
                                    ruuid: resuuid,
                                    stype: stype,
                                    sdtoken: sdtoken,
                                    params: JSON.stringify({
                                        'mod': 'skill',
                                        'cont': '[]'
                                    })
                                },
                                function (d) {
                                    d = JSON.parse(d);
                                    if (d.code == 100) {
                                        resuuid = d.msg.ruuid;
                                        resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                        $('.return').attr('href', '/my');
                                        dataSkillHobby = '';
                                    } else {
                                        popup1('删除失败！');
                                    }
                                    $.get('/getsdtoken?' + Math.random(), function (d) {
                                        sdtoken = d;
                                    });
                                });
                            break;
                        case $opus[0]:
                            $.post(postUrl, {
                                    guuid: groupuuid,
                                    ruuid: resuuid,
                                    stype: stype,
                                    sdtoken: sdtoken,
                                    params: JSON.stringify({
                                        'mod': 'prod',
                                        'cont': '[]'
                                    })
                                },
                                function (d) {
                                    d = JSON.parse(d);
                                    if (d.code == 100) {
                                        resuuid = d.msg.ruuid;
                                        resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                        $('.return').attr('href', '/my');
                                        dataOpus = '';
                                    } else {
                                        popup1('删除失败！');
                                    }
                                    $.get('/getsdtoken?' + Math.random(), function (d) {
                                        sdtoken = d;
                                    });
                                });
                            break;
                        case $personalEv[0]:
                            $.post(postUrl, {
                                    guuid: groupuuid,
                                    ruuid: resuuid,
                                    stype: stype,
                                    sdtoken: sdtoken,
                                    params: JSON.stringify({
                                        'mod': 'desc',
                                        'cont': JSON.stringify({'judge': ''})
                                    })
                                },
                                function (d) {
                                    d = JSON.parse(d);
                                    if (d.code == 100) {
                                        resuuid = d.msg.ruuid;
                                        resumeIntegrity(d.msg.score,d.msg.base_info, d.msg.expectjob,d.msg.eduinfo);
                                        $('.return').attr('href', '/my');
                                        dataPersonalEv = '';
                                    } else {
                                        popup1('删除失败！');
                                    }
                                    $.get('/getsdtoken?' + Math.random(), function (d) {
                                        sdtoken = d;
                                    });
                                });
                            break;
                    }
                })
                $('.no6').off('click').on('click', function () {
                    $('.cover').fadeOut(300).find('.popup6').fadeOut(300);
                })
            } else {
                $this.parent().remove();
            }
        }
    })
    //增加一条
    $('.add').on('click', function () {
        var $this = $(this);
        $this.siblings('.content-box').find('.delete').show();
        //改变form的id
        var curId = $this.parent('.edit').find('.content-box').eq(0).find('form').attr('id');
        var num = parseInt(curId.substr(curId.length - 1, 1));
        num++;
        //判断当前在哪个模块，就用哪个模板
        var data = {num: [num]}, module = $this.parents('.module-item')[0], html;
        switch (module) {
            case $eduBg[0]:
                if ($this.siblings('.content-box').length === userExpand.edu_max_num - 1) {
                    $this.hide();
                }
                html = template('tpl3', data);
                break;
            case $practiceEx[0]:
                if ($this.siblings('.content-box').length === userExpand.practise_max_num - 1) {
                    $this.hide();
                }
                html = template('tpl4', data);
                break;
            case $academicEx[0]:
                if ($this.siblings('.content-box').length === userExpand.project_max_num - 1) {
                    $this.hide();
                }
                html = template('tpl5', data);
                break;
            case $schoolEx[0]:
                if ($this.siblings('.content-box').length === userExpand.exp_max_num - 1) {
                    $this.hide();
                }
                html = template('tpl6', data);
                break;
            case $skillHobby[0]:
                if ($this.siblings('.content-box').length === userExpand.skill_max_num - 1) {
                    $this.hide();
                }
                html = template('tpl7', data);
                break;
            case $opus[0]:
                if ($this.siblings('.content-box').length === userExpand.product_max_num - 1) {
                    $this.hide();
                }
                html = template('tpl8', data);
                break;
        }
        $(this).parents('.edit').prepend(html);
        scroll($(module));
        //重新初始化专业类别选择框和在校时间
        var conBox = $(this).parents('.edit').find('.content-box').eq(0);
        if (module === $eduBg[0]) {
            majorSel(conBox.find('.major-type'));
            schoolTime(conBox);
        }
        //重新初始化实习经历实习时间&&学术经历项目时间&&校园经历活动时间
        if (module === $practiceEx[0] || module === $academicEx[0] || module === $schoolEx[0]) {
            practiceTime(conBox);
        }
        selAutoSize();
    })
    //模块预览点击编辑
    $('.module-item .edit_btn').on('click', function () {
        var $this = $(this), $preview = $this.parent('.module-name').siblings('.preview'),
            $con = $preview.find('.content'), $module = $this.parents('.module-item'), num = 3;
        //判断当然有没有模块处于编辑状态
        isedit();
        $.each($('.main').find('.edit'), function (i, v) {
            if ($(v).is(':visible')) {
                var name = $(v).parent('.module-item').find('.module-name b').text();
                popup1(name + '处于编辑状态！');
                isEdit = true;
                return false;
            }
        })
        if (!isEdit) {
            $this.hide();
            scroll($module);
            if ($preview.is(':hidden')) {
                clear($preview.siblings('.edit'));
                $preview.siblings('.edit').show();
                if ($module[0] === $expectPractice[0]) {
                    epTime();
                    $expectPractice.find('select[name="day"]').val('5');
                    $expectPractice.find('input[name="salary"]').val('100');
                    dataSel();
                }
                if ($module[0] === $skillHobby[0]) {
                    html = template('tpl7', {num: [1]});
                    $skillHobby.find('.content-box').remove();
                    $skillHobby.find('.add').before(html);
                }
                cancelFlag = false;
            } else {
                cancelFlag = true;
                //计算有几条信息 就对应生成几条编辑块
                var list = [], num1 = 0, data = {}, html;
                //判断点击编辑的是哪一个模块
                switch ($module[0]) {
                    case $basicInfo[0]:
                        $.each($con, function (i, v) {
                            var birthday = $(this).find('.birthday').text().split('-'),
                                src = $(this).siblings('.head').find('img').attr('src'),
                                picId = src.substring(src.lastIndexOf("/") + 1, src.length).split('.')[0];
                            if(picId === 'default_headpic') picId = '';
                            list[i] = {
                                name: $(this).find('.name').text(),
                                gender: $(this).find('.gender').text(),
                                birthdayY: birthday[0],
                                birthdayM: birthday[1],
                                nation: $(this).find('.nation').text(),
                                city: $(this).find('.city').text(),
                                phone: $(this).find('.phone').text(),
                                email: $(this).find('.email').text(),
                                picId: picId,
                                num: i + 1
                            };
                        });
                        data = {list: list};
                        html = template('tpl1', data);
                        $module.find('.content-box').remove();
                        $module.find('.btn-box').before(html);
                        birthday();
                        if (data.list[0].nation && data.list[0].nation !== '中国') {
                            $module.find('input[name="city"]').attr('data-selector', "").removeAttr('readonly');
                        }
                        //回显select框数据
                        $module.find('select[name="gender"]').val(data.list[0].gender);
                        $module.find('select[name="birthday-n"]').val(data.list[0].birthdayY);
                        $module.find('select[name="birthday-y"]').val(data.list[0].birthdayM);
                        $module.find('select[name="nation"]').val(data.list[0].nation);
                        break;
                    case $expectPractice[0]:
                        $.each($con, function (i, v) {
                            var time1 = $(this).find('.time span').eq(0).text().split('.'),
                                time2 = $(this).find('.time span').eq(1).text().split('.'),
                                post = $(this).find('.post span').text().split(','),
                                area = $(this).find('.area span').text().split(',');
                            list[i] = {
                                post: post,
                                area: area,
                                day: $(this).find('.day span').text().substr(0, 1),
                                time1Y: time1[0],
                                time1M: time1[1],
                                time2Y: time2[0],
                                time2M: time2[1],
                                salary: $(this).find('.salary>span>span').text(),
                                goTime: $(this).find('.go-time span').text(),
                                num: i + 1
                            };
                        });
                        data = {list: list};
                        html = template('tpl2', data);
                        $module.find('.content-box').remove();
                        $module.find('.btn-box').before(html);
                        //切换期望职位和期望地点的添加删除按钮
                        var $post = $module.find('.edit .post').eq(0), $area = $module.find('.edit .area').eq(0);
                        $post.find('span').text('期望职位');
                        $area.find('span').text('期望地点');
                        $post.siblings('.post').find('.add-post').text('删除').removeClass('add-post').addClass('del-post');
                        $area.siblings('.area').find('.add-area').text('删除').removeClass('add-area').addClass('del-area');
                        if ($module.find('.edit .post').length === 3) {
                            $post.find('.add-post').hide();
                        }
                        if ($module.find('.edit .area').length === 3) {
                            $area.find('.add-area').hide();
                        }
                        epTime();
                        $module.find('select[name="day"]').val(data.list[0].day);
                        $module.find('select[name="time-start-n"]').val(data.list[0].time1Y);
                        $module.find('select[name="time-start-y"]').val(data.list[0].time1M);
                        $module.find('select[name="time-end-n"]').val(data.list[0].time2Y);
                        $module.find('select[name="time-end-y"]').val(data.list[0].time2M);
                        epPost();
                        dataSel();
                        break;
                    case $eduBg[0]:
                        num = userExpand.edu_max_num;
                        $.each($con, function (i, v) {
                            list[i] = {
                                school: $(this).find('.school span').eq(0).text(),
                                isAbroad: $(this).find('.school span').eq(1).text(),
                                schoolTime1: $(this).find('.school-time span').eq(0).text(),
                                schoolTime2: $(this).find('.school-time span').eq(1).text(),
                                education: $(this).find('.education span').text(),
                                majorType1: $(this).find('.major-type span').eq(0).text(),
                                majorType2: $(this).find('.major-type span').eq(1).text(),
                                major: $(this).find('.major span').text(),
                                score: $(this).find('.score span').text(),
                                course: $(this).find('.course span').text(),
                                honor: $(this).find('.honor span').text(),
                                num: i + 1
                            };
                        });
                        data = {list: list};
                        num1 = data.list[data.list.length - 1].num;
                        html = template('tpl3', data);
                        $module.find('.content-box').remove();
                        $module.find('.add').before(html);
                        schoolTime($module);
                        if (num1 >= 2) {
                            $module.find('.content-box').find('.delete').show();
                        }
                        $.each(data.list, function (i, v) {
                            majorSel($module.find('.content-box').eq(i).find('.major-type'));
                            var $conBox = $module.find('.content-box').eq(i);
                            $conBox.find('select[name="education"]').val(v.education);
                            $conBox.find('select[name="score"]').val(v.score);
                            $conBox.find('.major-type .sel-box1 select').val(v.majorType1).change();
                            $conBox.find('.major-type .sel-box2 select').val(v.majorType2);
                            $conBox.find('select[name="school-time-start"]').val(v.schoolTime1);
                            $conBox.find('select[name="school-time-end"]').val(v.schoolTime2);
                        })
                        break;
                    case $practiceEx[0]:
                        num = userExpand.practise_max_num;
                        $.each($con, function (i, v) {
                            var time1 = $(this).find('.time span').eq(0).text().split('.'),
                                time2 = $(this).find('.time span').eq(1).text().split('.');
                            list[i] = {
                                company: $(this).find('.company span').text(),
                                position: $(this).find('.position span').text(),
                                time1Y: time1[0],
                                time1M: time1[1],
                                time2Y: time2[0],
                                time2M: time2[1],
                                place: $(this).find('.place span').text(),
                                duty: $(this).find('.duty span').text(),
                                num: i + 1
                            };
                        });
                        data = {list: list};
                        html = template('tpl4', data);
                        $module.find('.content-box').remove();
                        $module.find('.add').before(html);
                        practiceTime($module);
                        $.each(data.list, function (i, v) {
                            var $conBox = $module.find('.content-box').eq(i);
                            $conBox.find('select[name="time-start-n"]').val(v.time1Y);
                            $conBox.find('select[name="time-start-y"]').val(v.time1M);
                            $conBox.find('select[name="time-end-n"]').val(v.time2Y);
                            $conBox.find('select[name="time-end-y"]').val(v.time2M);
                        })
                        break;
                    case $academicEx[0]:
                        num = userExpand.project_max_num;
                        $.each($con, function (i, v) {
                            var time1 = $(this).find('.time span').eq(0).text().split('.'),
                                time2 = $(this).find('.time span').eq(1).text().split('.');
                            list[i] = {
                                name: $(this).find('.name span').text(),
                                position: $(this).find('.position span').text(),
                                time1Y: time1[0],
                                time1M: time1[1],
                                time2Y: time2[0],
                                time2M: time2[1],
                                place: $(this).find('.place span').text(),
                                des: $(this).find('.des span').text(),
                                num: i + 1
                            };
                        });
                        data = {list: list};
                        html = template('tpl5', data);
                        $module.find('.content-box').remove();
                        $module.find('.add').before(html);
                        practiceTime($module);
                        $.each(data.list, function (i, v) {
                            var $conBox = $module.find('.content-box').eq(i);
                            $conBox.find('select[name="time-start-n"]').val(v.time1Y);
                            $conBox.find('select[name="time-start-y"]').val(v.time1M);
                            $conBox.find('select[name="time-end-n"]').val(v.time2Y);
                            $conBox.find('select[name="time-end-y"]').val(v.time2M);
                        })
                        break;
                    case $schoolEx[0]:
                        if(exp_info.length!=0){
                            if(!(exp_info[0].begin_year&& exp_info[0].begin_month)){
                                $this.show();
                                $('.cover').fadeIn(300).find('.popup7').fadeIn(300);
                                $('.yes7').off('click').on('click',function () {
                                    $this.hide();
                                    $('.cover').fadeOut(300).find('.popup7').fadeOut(300);
                                    fn1();
                                    if ($con.length >= num) {
                                        $module.find('.add').hide();
                                    }
                                    $this.parent('.module-name').siblings('.preview').hide().siblings('.edit').show();
                                    selAutoSize();
                                })
                                $('.no7').off('click').on('click',function () {
                                    $('.cover').fadeOut(300).find('.popup7').fadeOut(300);
                                })
                                return;
                            }
                        }
                        function fn1() {
                            num = userExpand.exp_max_num;
                            $.each($con, function (i, v) {
                                var time1 = $(this).find('.time span').eq(0).text().split('.'),
                                    time2 = $(this).find('.time span').eq(1).text().split('.');
                                list[i] = {
                                    name: $(this).find('.name span').text(),
                                    position: $(this).find('.position span').text(),
                                    time1Y: time1[0],
                                    time1M: time1[1],
                                    time2Y: time2[0],
                                    time2M: time2[1],
                                    place: $(this).find('.place span').text(),
                                    des: $(this).find('.des span').text(),
                                    num: i + 1
                                };
                            });
                            data = {list: list};
                            html = template('tpl6', data);
                            $module.find('.content-box').remove();
                            $module.find('.add').before(html);
                            practiceTime($module);
                            $.each(data.list, function (i, v) {
                                var $conBox = $module.find('.content-box').eq(i);
                                $conBox.find('select[name="time-start-n"]').val(v.time1Y);
                                $conBox.find('select[name="time-start-y"]').val(v.time1M);
                                $conBox.find('select[name="time-end-n"]').val(v.time2Y);
                                $conBox.find('select[name="time-end-y"]').val(v.time2M);
                            })
                        }
                        fn1();
                        break;
                    case $skillHobby[0]:
                        if(skill_info.length!=0){
                            if(!(skill_info[0].info&& skill_info[0].type)){
                                $this.show();
                                $('.cover').fadeIn(300).find('.popup7').fadeIn(300);
                                $('.yes7').off('click').on('click',function () {
                                    $this.hide();
                                    $('.cover').fadeOut(300).find('.popup7').fadeOut(300);
                                    fn2();
                                    if ($con.length >= num) {
                                        $module.find('.add').hide();
                                    }
                                    $this.parent('.module-name').siblings('.preview').hide().siblings('.edit').show();
                                    selAutoSize();
                                })
                                $('.no7').off('click').on('click',function () {
                                    $('.cover').fadeOut(300).find('.popup7').fadeOut(300);
                                })
                                return;
                            }
                        }
                        function fn2() {
                            num = userExpand.skill_max_num;
                            $.each($con.find('p'), function (i, v) {
                                var name = $(this).find('i').text();
                                if (name === '语言水平') {
                                    var des = '';
                                    $.each($(this).find('span'), function (index, value) {
                                        des += $(value).text() + ',';
                                    })
                                    list[i] = {
                                        name: name,
                                        des: des,
                                        num: i + 1
                                    };
                                } else {
                                    list[i] = {
                                        name: name,
                                        des: $(this).find('span').text(),
                                        num: i + 1
                                    };
                                }
                            });
                            data = {list: list};
                            html = template('tpl7', data);
                            $module.find('.content-box').remove();
                            $module.find('.add').before(html);
                            $.each(data.list, function (i, v) {
                                $module.find('.content-box').eq(i).find('select[name="type"]').val(v.name);
                                if (v.name === '语言水平') {
                                    var $conBox = $module.find('.content-box').eq(i), l = $conBox.find('.language');
                                    l.eq(0).find('.del-language').hide();
                                    if (l.length >= 3) l.siblings('.add-language').hide();
                                    var arr = v.des.substring(0, v.des.length - 1).split(',');
                                    $.each(arr, function (index, value) {
                                        language($module.find('.content-box').eq(i).find('.language').eq(index));
                                        var arr1 = value.split(/\s/);
                                        $conBox.find('select[name="language_type"]').eq(index).val(arr1[0]).change();
                                        $conBox.find('select[name="language_level"]').eq(index).val(arr1[1]);
                                        var score = arr1[2] ? arr1[2] : arr1[1];
                                        $conBox.find('input[name="score"]').eq(index).val(score.substring(1, score.length - 1));
                                    })
                                }
                            })
                            if ($con.find('p').length >= num) {
                                $module.find('.add').hide();
                            }
                        }
                        fn2();
                        break;
                    case $opus[0]:
                        num = userExpand.product_max_num;
                        $.each($con, function (i, v) {
                            list[i] = {
                                link: $(this).find('p.link a').text(),
                                des: $(this).find('p.des span').text(),
                                num: i + 1
                            };
                        });
                        data = {list: list};
                        html = template('tpl8', data);
                        $module.find('.content-box').remove();
                        $module.find('.add').before(html);
                        break;
                    case $personalEv[0]:
                        list[0] = {
                            des: $con.find('p').text(),
                            num: 1
                        };
                        data = {list: list};
                        html = template('tpl9', data);
                        $module.find('.content-box').remove();
                        $module.find('.btn-box').before(html);
                        break;
                }
                if ($con.length >= num) {
                    $module.find('.add').hide();
                }
                $this.parent('.module-name').siblings('.preview').hide().siblings('.edit').show();
            }
            //重新初始化textarea框自适应高度
            selAutoSize();
        }
    })

    //点击添加期望职位
    $expectPractice.on('click', '.add-post', function () {
        var html = template('tpl2-2');
        $(this).parent('.post').siblings('.area').eq(0).before(html);
        if ($(this).parent('.post').siblings('.post').length >= 2) {
            $(this).hide();
        }
        epPost();
    })
    //点击删除期望职位
    $expectPractice.on('click', '.del-post', function () {
        $(this).parent('.post').remove();
        $('.add-post').show();
    })
    //点击添加期望地点
    $expectPractice.on('click', '.add-area', function () {
        var html = template('tpl2-3');
        $(this).parent('.area').siblings('.day').eq(0).before(html);
        if ($(this).parent('.area').siblings('.area').length >= 2) {
            $(this).hide();
        }
    })
    //点击删除期望地点
    $expectPractice.on('click', '.del-area', function () {
        $(this).parent('.area').remove();
        $('.add-area').show();
    })
    //点击添加一门语言
    $skillHobby.on('click', '.add-language', function () {
        var $this = $(this);
        if ($this.siblings('.language').length >= 2) {
            $this.hide();
        }
        var html = template('tpl7-2');
        $this.before(html);
        $this.siblings('.language:gt(0)').find('.del-language').show();
        language($this.prev());
    })
    //点击删除一门语言
    $skillHobby.on('click', '.del-language', function () {
        var l = $(this).parent('.language');
        l.siblings('.add-language').show();
        l.remove();
    })

    //头像上传
    // var resume_pic_uploader = WebUploader.create({
    //     auto: true, //自动上传
    //     swf: '/static/js/Uploader.swf',
    //     server: '/upload',
    //     pick: '#resumePicpicker',
    //     resize: true,  // 是否压缩
    //     accept: {
    //         title: 'Images',
    //         extensions: 'gif,jpg,jpeg,bmp,png',
    //         mimeTypes: 'image/*'
    //     }
    // });
    // resume_pic_uploader.on('uploadSuccess', function (file, response) {
    //     if (response.code == 100) {
    //         var logo_url = '//sxsimg.xiaoyuanzhao.com/' + response.msg[1];
    //         $('#headpic').val(response.msg[0]);
    //         $('#img_upload').attr('src', logo_url);
    //     }
    // });
    //相关附件上传
    if ($attachment.find('.preview ul.list').find('li').length >= 2) {
        $('#attachPicker').hide();
    }
    //上传成功，获取ajax返回值
    var attach_uploader = WebUploader.create({
        auto: true, //自动上传
        swf: '/static/js/Uploader.swf',
        server: '/upload',
        pick: '#attachPicker',
        resize: true,  // 是否压缩
        accept: {
            // title: 'Images',
            extensions: 'jpg,png,jpeg,pdf,doc,docx,txt',
            mimeTypes: 'image/*,application/pdf,application/msword,application/octet-stream,text/plain,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
        }
    });
    //添加队列前判断是否可以上传
    attach_uploader.on('beforeFileQueued', function (file) {
        if (file.size > 5 * 1024 * 1024) {
            popup1('简历大小大于5M,请重新上传!');
            return false;
        }
    });
    attach_uploader.on('uploadSuccess', function (file, response) {

        if (response.code == 100) {
            isedit();
            if (!isEdit) {
                var sid = '';
                $.post('/trainee/resume/attach/add', {
                    ruuid: resuuid,
                    params: JSON.stringify({
                        'name': file.name,
                        'filepath': response.msg[0]
                    })
                }, function (data) {
                    if (data.code == 100) {
                        sid = data.msg.sid;
                        var li = '<li data-msg=' + data.msg.path + '><div class="name"><span class="icon"></span>' + file.name + '</div><span class="delete" data-attuuid=' + sid + '>删除</span></li>';
                        $attachment.find('.preview ul.list').append(li);
                        var l = $attachment.find('.preview ul.list').find('li').length;
                        if (l >= userExpand.attach_max_num) {
                            $('#attachPicker').hide();
                        }
                        dataAtta = JSON.parse(dataAtta);
                        dataAtta.push({"name": file.name, "old_sid": sid, "filepath": response.msg[0]});
                        dataAtta = JSON.stringify(dataAtta);
                    } else {
                        popup1("上传出错！");
                    }
                    $.get('/getsdtoken?' + Math.random(), function (d) {
                        sdtoken = d;
                    });
                }, 'json')
            }
        }
    });
    attach_uploader.on('uploadError', function (file) {
        popup1("上传失败！");
    });
    // 删除上传附件
    $attachment.on('click', '.delete', function () {
        var $this = $(this);
        $.post('/trainee/resume/attach/delect', {
            ruuid: resuuid,
            params: JSON.stringify({
                'sid': $this.attr('data-attuuid')
            })
        }, function (data) {
            if (data.code == 100) {
                $this.parents('li').remove();
                $('#attachPicker').show();
            } else {
                popup1('删除失败！')
            }
        }, 'json')
    });

    //简历完善度
    var newMenFlag = true;

    function resumeIntegrity(score, base_info, expect, edu_info) {
        if (score >= 60 && base_info && expect && edu_info) {
            $('.integrity .state').text('（可投递）');
        } else {
            $('.integrity .state').text('（不可投递）');
        }
        $('.integrity .value').text(score + '%');
        $('.integrity .line').css('width', score * 2 + 'px');
    }

    function giftbag(score, isdefault, baseinfo, expect_job, education) {
        if (newMenFlag && is_show_gift_bag && isdefault && score >= 60 && baseinfo && expect_job && education) {
            $('.cover').fadeIn(300).find('.popup4').slideDown(300);
            $.post('/show/gift/popup', function (d) {
                if (d.code == 100) {
                    newMenFlag = false;
                    is_show_gift_bag = false
                }
            }, 'json')
        }
    }

    //弹窗2
    $('.cover').on('click', function () {
        $(this).fadeOut(300).children().fadeOut(300);
    })
    $('.cover').children().on('click', function (e) {
        e.stopPropagation();
    })
    $('.popup2').on('click', '.no', function () {
        $('.cover').fadeOut(300).children().fadeOut(300);
    })
    $('.popup2').on('click', '.yes', function () {
        $('.cover').fadeOut(300).children().fadeOut(300);
    })
    //弹窗3
    $('.popup3').on('click', 'a.btn', function () {
        $('.cover').fadeOut(300).children().fadeOut(300);
    })

    $eduBg.on('click', '.isabroad', function () {
        $(this).toggleClass('active');
    })

    //查看示例
    $('.see-demo').on('click', function () {
        $(this).siblings('.demo-con').slideToggle(300);
    })
    $('.demo-box').on('click', function (e) {
        e.stopPropagation();
    })
    $('html,body').on('click', function () {
        $('.demo-con').slideUp(300);
    })

    function scroll(el, t) {
        t ? t : 300;
        $('html,body').animate({scrollTop: el.position().top}, t);
    }

    //判断当前有没有模块处于编辑状态方法
    var isEdit = false;

    function isedit() {
        isEdit = false;
        $.each($('.main').find('.edit'), function (i, v) {
            if ($(v).is(':visible')) {
                var name = $(v).parent('.module-item').find('.module-name b').text();
                popup1(name + '处于编辑状态！');
                isEdit = true;
                return false;
            }
        })
    }

    //清空编辑框数据方法
    function clear(el) {
        el.find('input,select,textarea').val('');
    }

    //英文简历教育背景隐藏未填项方法
    function hideTitle(el) {
        $.each($eduBg.find('.preview .content'),function () {
            var text = $(this).find(el).find('span').text();
            if (!text || /^\s+$/.test(text)) {
                $(this).find(el).hide();
            }
        })
    }

    //附件简历
    var $list = $('#thelist');
    var uploader = WebUploader.create({
        auto: true, //自动上传
        // swf文件路径
        swf: '/static/js/Uploader.swf',
        // 文件接收服务端。
        server: '/upload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#resume-picker',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        // 只允许选择文件，可选。
        accept: {
            title: 'Applications',
            extensions: 'pdf,doc,docx,ppt,pptx',
            mimeTypes: 'application/pdf,application/msword,application/octet-stream,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
        }
    });
    uploader.on('beforeFileQueued', function (file) {
        if (file.size > 5 * 1024 * 1024) {
            popup1('简历大小大于5M,请重新上传!');
            return false;
        }
    });

    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function (file) {
        $list.html('<div id="' + file.id + '" class="item">' +
            '<p class="info">' + file.name +
            '<span class="state">等待上传...</span>' + '</p>' +
            '</div>');

    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('span.state').text('上传中');

        $percent.css('width', percentage * 100 + '%');
    });
    //上传成功，获取ajax返回值
    uploader.on('uploadSuccess', function (file, response) {
        if (response.code == 100) {
            var fileuuid = response.msg[0],
                fileurl = response.msg[1];
            att_resume_onfinish(true, fileuuid, fileurl);
            $('#' + file.id).find('span.state').text('已上传');
            $('.webuploader-pick').text('重新上传');
        }
    });
    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('span.state').text('上传出错');
        alert('aaaaaa')
    });
    uploader.on('error', function (code) {
        if (code == "Q_TYPE_DENIED") {
            popup1('不支持上传该格式的文件哦~')
        }
        else {
            popup1('Error:' + code)
        }
    });
    attach_uploader.on('error', function (code) {
        if (code == "Q_TYPE_DENIED") {
            popup1('不支持上传该格式的文件哦~')
        }
        else {
            popup1('Error:' + code)
        }
    });
    uploader.onError = function (code) {
        if (code == "Q_TYPE_DENIED") {
            popup1('不支持上传该格式的文件哦~')
        }
        else {
            popup1('Error:' + code)
        }

    };
    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });

    function att_resume_onfinish(success, fileuuid, fileurl) {
        if (success) {
            var ruuid = $('#gdata').attr('data-ruuid');
            $(".download").attr("data-att", '1');
            $.post('/trainee/attach/resume/add',
                {
                    ruuid: ruuid,
                    params: JSON.stringify({'filepath': fileuuid})
                }, function (data) {
                    if (data.code == 100) {
                        popup1('上传成功！');

                        $('#iframe').attr('src', data.msg);
                    }
                    else {
                        popup1(data.msg);
                    }
                },
                //返回类型
                "json"
            );
        } else {
            popup1(fileuuid);
        }

    }

    //预览
    $(".operation .preview").on('click', function () {
        if ($(this).attr("data-pre") == '0') {
            popup1('请先完善信息！');
        } else {
            window.open($(this).attr("data-href"));
        }
    })
    //下载
    $('.download').on('click', function (e) {
        if ($(this).attr("data-att") == '0') {
            popup1($(this).attr("data-msg"));
        } else {
            window.open($(this).attr("data-href"));
        }
    })
    //点击返回个人中心
    // $('.nav .return').on('click',function (e) {
    //     e.preventDefault();
    //     isedit();
    //     if(!isEdit){
    //         window.location.href = "/my";
    //     }
    // })
})