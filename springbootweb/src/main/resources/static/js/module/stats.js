Stats = {
	loanLoanOrderInfoSucPerMonthChart:function(){
		 Net.get({
			 url:'/stats/loan_order_suc_per_month',
			 success:function(resp){
				 console.log(  resp );
				 switch (resp.status) {
				 	case 0:
				 		var data = resp.data;
				 		var option = {
						    tooltip : {
						        trigger: 'axis',
						        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						        }
						    },
						    legend: {
						        data:['APP录单','PC录单']
						    },
						    //color:['#6e7074','#61a0a8'],
						    grid: {
						        left: '3%',
						        right: '4%',
						        bottom: '3%',
						        containLabel: true
						    },
						    xAxis : [
						        {
						            type : 'category',
						            data : data.catagoryList
						        }
						    ],
						    yAxis : [
						        {
						            type : 'value'
						        }
						    ],
						    series : [
						        {
						            name:'APP录单',
						            type:'bar',
						            label: {
						                normal: {
						                    show: true,
						                    position: 'top'
						                }
						            },
						            data: data.appDataList
						        },
						        {
						            name:'PC录单',
						            type:'bar',
						            label: {
						                normal: {
						                    show: true,
						                    position: 'top'
						                }
						            },
						            data:data.pcDataList
						        }
						    ]
						};
				 		var myChart = echarts.init(document.getElementById('loanOrderSucChart'));
				 		myChart.setOption(option);
				 		$('#monthStatsLoading').remove();
				 		break;
				 	default:
				 		break;
				}
			 }
		 })
	},
	userPerMonthChart:function(){
		Net.get({
			url:'/stats/user_per_month',
			success:function(resp){
				switch (resp.status) {
				case 0:
					var data = resp.data;
					var option = {
							tooltip : {
								trigger: 'axis',
								axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
								}
							},
							legend: {
								data:['销售','运营']
							},
							//color:['#6e7074','#61a0a8'],
							grid: {
								left: '3%',
								right: '4%',
								bottom: '3%',
								containLabel: true
							},
							xAxis : [
							         {
							        	 type : 'category',
							        	 data : data.catagoryList
							         }
							         ],
							         yAxis : [
							                  {
							                	  type : 'value'
							                  }
							                  ],
							                  series : [
							                            {
							                            	name:'销售',
							                            	type:'line',
							                            	label: {
							                            		normal: {
							                            			show: true,
							                            			position: 'top'
							                            		}
							                            	},
							                            	data: data.staffDataList
							                            },
							                            {
							                            	name:'运营',
							                            	type:'line',
							                            	label: {
							                            		normal: {
							                            			show: true,
							                            			position: 'top'
							                            		}
							                            	},
							                            	data:data.sysUserDataList
							                            }
							                            ]
					};
					var myChart = echarts.init(document.getElementById('userPerMonthChart'));
					myChart.setOption(option);
					$('#userPerMonthChartLoading').remove();
					break;
				default:
					break;
				}
			}
		})
	},
	dealerPerMonthChart:function(){
		Net.get({
			url:'/stats/dealer_per_month',
			success:function(resp){
				switch (resp.status) {
					case 0:
						var data = resp.data;
						var option = {
							color: ['#3398DB'],
							tooltip : {
								trigger: 'axis',
								axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
								}
							},
							grid: {
								left: '3%',
								right: '4%',
								bottom: '3%',
								containLabel: true
							},
							xAxis : [
								{
									type : 'category',
									data : data.catagoryList,
									axisTick: {
										alignWithLabel: true
									}
								}
							],
							yAxis : [
								{
									type : 'value'
								}
							],
							series : [
								{
									name:'新增门店数量',
									type:'bar',
									barWidth: '60%',
									label: {
										normal: {
											show: true,
											position: 'top'
										}
									},
									data: data.dealerList
								}
							]
						};
						var myChart = echarts.init(document.getElementById('dealerPerMonthChart'));
						myChart.setOption(option);
						$('#dealerStatsLoading').remove();
						break;
					default:
						break;
				}
			}
		})
	}
}
