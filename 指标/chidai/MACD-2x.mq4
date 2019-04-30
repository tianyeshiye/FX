#property  indicator_separate_window
#property  indicator_buffers 4
#property  indicator_color1  DarkGray
#property  indicator_color2  Red
#property  indicator_color3  Green
#property  indicator_color4  Yellow
#property  indicator_width1  1
extern int FastEMA=12;
extern int SlowEMA=26;
extern int SignalSMA=9;
double     MacdBuffer0[];
double     MacdBuffer1[];
double     MacdBuffer2[];
double     SignalBuffer[];
double     MacdBuffer[];
int nexttime=0;
int init()
  {
   IndicatorBuffers(5); 
   SetIndexStyle(0,DRAW_HISTOGRAM);
   SetIndexStyle(1,DRAW_HISTOGRAM);
   SetIndexStyle(2,DRAW_HISTOGRAM);
   SetIndexStyle(3,DRAW_LINE);
   SetIndexStyle(4,DRAW_NONE);
   IndicatorDigits(Digits+1);
   SetIndexBuffer(0,MacdBuffer0);
   SetIndexBuffer(1,MacdBuffer1);
   SetIndexBuffer(2,MacdBuffer2);
   SetIndexBuffer(3,SignalBuffer);
   SetIndexBuffer(4,MacdBuffer);
   IndicatorShortName("MACD("+FastEMA+","+SlowEMA+","+SignalSMA+")");
   SetIndexLabel(0,"MACD");
   SetIndexLabel(1,"MACD");
   SetIndexLabel(2,"MACD");   
   SetIndexLabel(3,"Signal");
   return(0);
  }

int start()
  {
   int limit;
   int counted_bars=IndicatorCounted();
   if(counted_bars>0) counted_bars--;
   limit=Bars-counted_bars;
   for(int i=0; i<limit; i++)
      MacdBuffer[i]=iMA(NULL,0,FastEMA,0,MODE_EMA,PRICE_CLOSE,i)-iMA(NULL,0,SlowEMA,0,MODE_EMA,PRICE_CLOSE,i);
   for(i=0; i<limit; i++)
      SignalBuffer[i]=iMAOnArray(MacdBuffer,Bars,SignalSMA,0,MODE_SMA,i);
   for(i=0; i<limit; i++)
    {
      if(MacdBuffer[i]>0 && MacdBuffer[i]>=SignalBuffer[i]) 
         {MacdBuffer1[i]=MacdBuffer[i]; MacdBuffer2[i]=0;MacdBuffer0[i]=0;}
      if(MacdBuffer[i]<0 && MacdBuffer[i]<=SignalBuffer[i]) 
         {MacdBuffer2[i]=MacdBuffer[i]; MacdBuffer1[i]=0;MacdBuffer0[i]=0;}
      if((MacdBuffer[i]>0 && MacdBuffer[i]<SignalBuffer[i])||(MacdBuffer[i]<0 && MacdBuffer[i]>SignalBuffer[i]))
         {MacdBuffer0[i]=MacdBuffer[i]; MacdBuffer1[i]=0;MacdBuffer2[i]=0;}
    }
    return(0);
  }
  
  

