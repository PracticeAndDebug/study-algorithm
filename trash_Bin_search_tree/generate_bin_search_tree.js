// generate full binery search tree
// 255 points
// then output 100 text files to the texts folder

max = 1000
min = 1
randmin=1000
randmax=-1000
level = 4
count= Math.pow(2,level)-1
fileNameHeader = "bin_search_tree_data_"
filePath = "./texts/"
fileCount=4

function randomInt(min,max){
  x=Math.random()
  x=x*(max-min+1)+min
  x=Math.floor( x )
  return x
}
function multiRandInt(min,max,count){
  arr=[]	
  for(i=0;i<count;i++){
    arr.push( randomInt(min,max) )
  }	
  return arr	
}

function testRandomInt(){
  for(i=0;i<1000;i++){
    randomInt( min,max )
    randmin = (x<randmin)?x:randmin
    randmax = (x>randmax)?x:randmax
  }
}

function genBinTree( index, level, objarr){
  count = index+1
  if( level >=2 ){
      (function(){
      offset = Math.pow(2,level-2);

      left_idx = index-offset;	  
      right_idx = index+offset;

      objarr[index].left = objarr[left_idx];	   
      objarr[index].right= objarr[right_idx];

      objarr[left_idx].boss= objarr[index];
      objarr[right_idx].boss= objarr[index];

      level_right = level-1;
      level_left = level-1;
      }());

      fuck(
	left_idx,
	right_idx,
	level_left,
	level_right,
	level,
	objarr
      );
  }	
}
function fuck(
	left_idx,
	right_idx,
	level_left,
	level_right,
	level,
	objarr
){
  genBinTree(left_idx,level_left,objarr);
  genBinTree(right_idx,level_right,objarr);
}

function bigwrong(str){
}

function confirm_bin_search_tree( point ){
  function traverse(point,func){
    avoid_bibao(point,func);	  
  }	
  function avoid_bibao(point,func){
    if(point.left  ){ traverse(point.left   ,func); }
    func(point);
    if(point.right ){ traverse(point.right  ,func); }
  }
  arr=[];
  traverse( root_point , point=>{arr.push(point.val)});
  flag = arr.every( (item,idx,arr) => idx==(arr.length-1) || item<=arr[idx+1] );
  	
  flag_1 = true;
  traverse( root_point , point=>{ if((!point.left)!=(!point.right)) flag_1 = false; }	)
  return flag && flag_1;
}

function view(){
  arr=multiRandInt(min,max,count);
  arr.sort( (a,b)=>(a-b) )	
  objarr = arr.map( x=>({val:x,left:null,right:null,boss:null}) )
  genBinTree( Math.pow(2,level-1)-1 , level, objarr);
  root_point = objarr[ (count+1)/2-1];
}

function writeFile(fileName,str){
  fs = require("fs");
  fs.writeFile(fileName,str,err=>{
    x="ok";
    if(err){x="fail"}
    //console.log(x);	  
  });
}

function writeObjArrToFile(fileName){
  arr=multiRandInt(min,max,count);
  arr.sort( (a,b)=>(a-b) )	
  objarr = arr.map( (x,idx,arr)=>({val:x,left:null,right:null,boss:null,index:idx}) )
  genBinTree( Math.pow(2,level-1)-1 , level, objarr);
  root_point = objarr[ (count+1)/2-1];
  //console.log( "confirm:"+confirm_bin_search_tree( root_point ));
  
  objToStrFunc = x => ( x.index+" "+(x.left? x.left.index:-1)+" "+(x.right?x.right.index:-1)+" "+x.val);
  str = objarr.map( objToStrFunc ).join("\n");
  writeFile(filePath+fileNameHeader+fileName,str)
}

function writeMultObjArrToFile(count){
  for(c=0;c<count;c++){
    (function(){
      writeObjArrToFile("_"+c);
      //console.log("writeMultObjArrToFile:"+c+" "+c);	  
    }());
  }
}

function doit(){
  writeMultObjArrToFile(fileCount);
}

doit();
