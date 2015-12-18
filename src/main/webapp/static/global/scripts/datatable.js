/***
Wrapper/Helper Class for datagrid based on jQuery Datatable Plugin
***/
var Datatable = function() {

    var tableOptions; // main options
    var dataTable; // datatable object
    var table; // actual table jquery object
    var tableContainer; // actual table container object
    var tableWrapper; // actual table wrapper jquery object
    var tableInitialized = false;
    var ajaxParams = {}; // set filter mode
    var the;

    var countSelectedRecords = function() {
        var selected = $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        var text = tableOptions.dataTable.language.metronicGroupActions;
        if (selected > 0) {
            $('.table-group-actions > span', tableWrapper).text(text.replace("_TOTAL_", selected));
        } else {
            $('.table-group-actions > span', tableWrapper).text("");
        }
    };

    return {

        //main function to initiate the module
        init: function(options) {

            if (!$().dataTable) {
                return;
            }

            the = this;

            // default settings
            options = $.extend(true, {
                src: "", // actual table  
                filterApplyAction: "filter",
                filterCancelAction: "filter_cancel",
                resetGroupActionInputOnSuccess: false,
                loadingMessage: '正在加载...',
                dataTable: {
                    "dom": "<'row'<'col-md-12 col-sm-12'<'table-group-actions pull-left'>>r><'table-scrollable't><'row'<'col-md-10 col-sm-12'pli><'col-md-2 col-sm-12'>>", // datatable layout
                    "pageLength": 10, // default records per page
                    "language": { // language settings
                        // metronic spesific
                        "metronicGroupActions": "选中_TOTAL_ 记录:  ",
                        "metronicAjaxRequestGeneralError": "请求失败，请检查您的网络连接",

                        // data tables spesific
                        "bStateSave": true,
                        "lengthMenu": "<span class='seperator'>|</span>每页 _MENU_ 条记录",
                        "info": "<span class='seperator'>|</span>总共 _TOTAL_ 记录",
                        "infoEmpty": "没有可以显示的记录",
                        "emptyTable": "没有记录",
                        "zeroRecords": "没有符合条件的记录",
                        "paginate": {
                            "previous": "上一页",
                            "next": "下一页",
                            "last": "最后一页",
                            "first": "第一页",
                            "page": "页码",
                            "pageOf": "/"
                        }
                    },

                    "orderCellsTop": true,
                    "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                        'orderable': false,
                        'targets': [0]
                    }],
                    "pagingType": "bootstrap_extended", // pagination type(bootstrap, bootstrap_full_number or bootstrap_extended)
                    "autoWidth": false, // disable fixed width and enable fluid table
                    "processing": false, // enable/disable display message box on record load
                    "serverSide": true, // enable/disable server side ajax loading

                    "ajax": { // define ajax settings
                        "url": "", // ajax URL
                        "type": "POST", // request type
                        "timeout": 20000,
                        "data": function(data) { // add request parameters before submit
                        	if (data.order != null) {
                        		// for miemiedev integration
                        		var orders = [];
                        		for(var i=0;i<data.order.length; i++) {
                        			var field = data.columns[data.order[i].column].data;
                            		var dir = data.order[i].dir;
                            		orders.push(field + "." + dir);
                        		}
                        		data.sortingOrder = orders.join();
                        	}
                        	delete data.order;
                        	delete data.columns;
                        	
                        	the.initFilterParams();
                        	
                        	$.each(ajaxParams, function(key, value) {
                                data[key] = value;
                            });
                        	
                            Metronic.blockUI({
                                message: tableOptions.loadingMessage,
                                target: tableContainer,
                                overlayColor: 'none',
                                cenrerY: true,
                                boxed: true
                            });                             
                        },
                        "dataSrc": function(res) { // Manipulate the data returned from the server                            
                            if (res.code == 0) {
                                if (tableOptions.resetGroupActionInputOnSuccess) {
                                    $('.table-group-action-input', tableWrapper).val("");
                                }
                            } else {
                            	 Metronic.alert({
                                     type: ('danger'),
                                     icon: ('warning'),
                                     message: res.message,
                                     container: tableWrapper,
                                     place: 'prepend'
                                 });
                            }

                            if ($('.group-checkable', table).size() === 1) {
                                $('.group-checkable', table).attr("checked", false);
                                $.uniform.update($('.group-checkable', table));
                            }

                            if (tableOptions.onSuccess) {
                                tableOptions.onSuccess.call(undefined, the, res);
                            }

                            Metronic.unblockUI(tableContainer);
                                                                               
                            return res.data;
                        },
                        "error": function() { // handle general connection errors
                        	alert('error');
                            if (tableOptions.onError) {
                                tableOptions.onError.call(undefined, the);
                            }

                            Metronic.alert({
                                type: 'danger',
                                icon: 'warning',
                                message: tableOptions.dataTable.language.metronicAjaxRequestGeneralError,
                                container: tableWrapper,
                                place: 'prepend'
                            });

                            Metronic.unblockUI(tableContainer);
                        }
                    },

                    "drawCallback": function(oSettings) { // run some code on table redraw
                        if (tableInitialized === false) { // check if table has been initialized
                            tableInitialized = true; // set table initialized
                            table.show(); // display table
                        }
                        Metronic.initUniform($('input[type="checkbox"]', table)); // reinitialize uniform checkboxes on each table reload
                        countSelectedRecords(); // reset selected records indicator

                        // callback for ajax data load
                        if (tableOptions.onDataLoad) {
                            tableOptions.onDataLoad.call(undefined, the);
                        }
                    }
                }
            }, options);
            
            table = $(options.src);
            
            //数据列映射和默认排序设置
            var columnDefs = [];
			var orders = [];
			var actions = [];
			
			$ ("th", table).each(function() {
				if ($(this).is('[data-action]')) {
					var col = {};
					col.targets = $(this).index();
					actions = $(this).attr("data-action").split(",");
					col.render = function(data, type, row) {
						var html = "<div data-row='"+JSON.stringify(row)+"'>";
						// action默认是class名称，用于定位
						for(var i in actions) {
							var action=actions[i];
							html += "<a style='padding-left:"+(i>0?6:0)+"px;' class='"+action+"'><i class='fa fa-"+action+"'></i></a>";
						}
						
						html += "</div>";
						return html;
					};
					
					columnDefs.push(col);
				}
				else if ($(this).is("[data-name]")) {
					var col = {};
					col.data = $(this).attr("data-name");
					col.targets = $(this).index();
					col.bSortable = $(this).is(".sortable");

					if ($(this).is("[data-codemap]")) {
						var codemap = $(this).attr("data-codemap");
						col.render = function(data, type, row) {
							return Helper.getCodeData(codemap, data);
						};
					}
					
					columnDefs.push(col);
				}

				if ($(this).is('[data-order]')) {
					var dir = $(this).attr("data-order");
					var order = [];
					order.push($(this).index(), dir);
					orders.push(order);
				}
			});
            
            options.dataTable.columnDefs = columnDefs;
            options.dataTable.order = orders;

            tableOptions = options;
            
            // create table's jquery object
            tableContainer = table.parents(".table-container");

            // apply the special class that used to restyle the default datatable
            var tmp = $.fn.dataTableExt.oStdClasses;

            $.fn.dataTableExt.oStdClasses.sWrapper = $.fn.dataTableExt.oStdClasses.sWrapper + " dataTables_extended_wrapper";
            $.fn.dataTableExt.oStdClasses.sFilterInput = "form-control input-small input-sm input-inline";
            $.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";

            // initialize a datatable
            dataTable = table.DataTable(options.dataTable);

            // revert back to default
            $.fn.dataTableExt.oStdClasses.sWrapper = tmp.sWrapper;
            $.fn.dataTableExt.oStdClasses.sFilterInput = tmp.sFilterInput;
            $.fn.dataTableExt.oStdClasses.sLengthSelect = tmp.sLengthSelect;

            // get table wrapper
            tableWrapper = table.parents('.dataTables_wrapper');

            // build table group actions panel
            if ($('.table-actions-wrapper', tableContainer).size() === 1) {
                $('.table-group-actions', tableWrapper).html($('.table-actions-wrapper', tableContainer).html()); // place the panel inside the wrapper
                $('.table-actions-wrapper', tableContainer).remove(); // remove the template container
            }
            // handle group checkboxes check/uncheck
            $('.group-checkable', table).change(function() {
                var set = $('tbody > tr > td:nth-child(1) input[type="checkbox"]', table);
                var checked = $(this).is(":checked");
                $(set).each(function() {
                    $(this).attr("checked", checked);
                });
                $.uniform.update(set);
                countSelectedRecords();
            });

            // handle row's checkbox click
            table.on('change', 'tbody > tr > td:nth-child(1) input[type="checkbox"]', function() {
                countSelectedRecords();
            });

            // handle filter submit button click
            tableWrapper.on('click', '.filter-submit', function(e) {
                e.preventDefault();
                the.submitFilter();
            });

            // handle filter cancel button click
            tableWrapper.on('click', '.filter-cancel', function(e) {
                e.preventDefault();
                the.resetFilter();
            });
            
            // 操作列点击注册
            for ( var i in actions) {
				table.on("click", 'div .' + actions[i], function(e) {
					if (tableOptions.onRowAction) {
						var rowData = $(this).parent().attr("data-row");
						var action = $(this).attr("class");
						tableOptions.onRowAction.call(undefined, the, action, JSON
								.parse(rowData));
					}
				});
			}
        },
        
        initFilterParams: function() {
        	// get all typeable inputs
            $('textarea.form-filter, select.form-filter, input.form-filter:not([type="radio"],[type="checkbox"])', tableWrapper).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all checkboxes
            $('input.form-filter[type="checkbox"]:checked', tableWrapper).each(function() {
                the.addAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all radio buttons
            $('input.form-filter[type="radio"]:checked', tableWrapper).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });
        },

        submitFilter: function() {
            the.setAjaxParam("action", tableOptions.filterApplyAction);
            the.initFilterParams();
            dataTable.ajax.reload();
        },

        resetFilter: function() {
            $('textarea.form-filter, select.form-filter, input.form-filter', tableWrapper).each(function() {
                $(this).val("");
            });
            $('input.form-filter[type="checkbox"]', tableWrapper).each(function() {
                $(this).attr("checked", false);
            });
            the.clearAjaxParams();
            the.addAjaxParam("action", tableOptions.filterCancelAction);
            dataTable.ajax.reload();
        },

        getSelectedRowsCount: function() {
            return $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        },

        getSelectedRows: function() {
            var rows = [];
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).each(function() {
                rows.push($(this).val());
            });

            return rows;
        },

        setAjaxParam: function(name, value) {
            ajaxParams[name] = value;
        },

        addAjaxParam: function(name, value) {
            if (!ajaxParams[name]) {
                ajaxParams[name] = [];
            }

            skip = false;
            for (var i = 0; i < (ajaxParams[name]).length; i++) { // check for duplicates
                if (ajaxParams[name][i] === value) {
                    skip = true;
                }
            }

            if (skip === false) {
                ajaxParams[name].push(value);
            }
        },

        clearAjaxParams: function(name, value) {
            ajaxParams = {};
        },

        getDataTable: function() {
            return dataTable;
        },

        getTableWrapper: function() {
            return tableWrapper;
        },

        gettableContainer: function() {
            return tableContainer;
        },

        getTable: function() {
            return table;
        },
        
        reload : function(){
            dataTable.ajax.reload();
        }
    };

};