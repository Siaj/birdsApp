animatedcollapse.addDiv('work-area-y1', 'fade=1')
animatedcollapse.addDiv('work-area-y2', 'fade=1')
animatedcollapse.addDiv('work-area-y3', 'fade=1')
animatedcollapse.addDiv('work-area-y4', 'fade=1')
animatedcollapse.addDiv('work-area-y5', 'fade=1')
animatedcollapse.addDiv('work-area-y6', 'fade=1')
animatedcollapse.addDiv('work-area-y7', 'fade=1')
animatedcollapse.addDiv('work-area-y8', 'fade=1')
animatedcollapse.addDiv('work-area-y9', 'fade=1')
animatedcollapse.addDiv('work-area-y10', 'fade=1')


animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}

animatedcollapse.init()
