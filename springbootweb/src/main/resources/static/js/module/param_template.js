ParamTemplate = {
	getRowTemplate:function(){
		var template = [];
		template.push('<li class="mg-top4">                                                                                                       ');
		template.push('	<div class="row">                                                                                                         ');
		template.push('		<div class="col-md-2">                                                                                                ');
		template.push('			<input class="form-control input-sm" name="paramKeys" value="" type="text" placeholder="key">                     ');
		template.push('		</div>                                                                                                                ');
		template.push('		<div class="col-md-3">                                                                                                ');
		template.push('			<input class="form-control input-sm" name="paramValues" value="" type="text" placeholder="value">                 ');
		template.push('		</div>                                                                                                                ');
		template.push('		<div class="col-md-4">                                                                                                ');
		template.push('			<input class="form-control input-sm" name="paramComments" value="" type="text" placeholder="comment">             ');
		template.push('		</div>                                                                                                                ');
		template.push('		<div class="col-md-3">                                                                                                ');
		template.push('            <div class="row">                                                                                              ');
		template.push('                <div class="col-md-6">                                                                                     ');
		template.push('                  <input type="checkbox" name="paramRequirement" checked="checked"/>                                       ');
		template.push('                  <input type="hidden" name="paramSend" value="true"/>                                                   ');
		template.push('                </div>                                                                                                     ');
		template.push('                <div class="col-md-6 js-param-optbar">                                                                     ');
		template.push('                    <a href="javascript:void(0)" class="js-param-send"><span class="fa fa-check-square-o"></span></a>&nbsp;&nbsp;');
		template.push('                    <a href="javascript:void(0)"><span class="fa fa-long-arrow-up"></span></a>&nbsp;&nbsp;                 ');
		template.push('                    <a href="javascript:void(0)"><span class="fa fa-long-arrow-down"></span></a>&nbsp;&nbsp;               ');
		template.push('                    <a href="javascript:void(0)" class="js-param-remove"><span class="fa fa-times"></span></a> &nbsp;&nbsp;');
		template.push('                </div>                                                                                                     ');
		template.push('            </div>                                                                                                         ');
		template.push('        </div>                                                                                                             ');
		template.push('	</div>                                                                                                                    ');
		template.push('</li>                                                                                                                      ');
		return template.join('');
	}                                                                                                                         
}
