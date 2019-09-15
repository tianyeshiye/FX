<chart>
id=131958466533983960
symbol=GBPJPY
period=240
leftpos=709
digits=2
scale=8
graph=1
fore=1
grid=0
volume=1
scroll=0
shift=0
ohlc=1
one_click=0
one_click_btn=1
askline=1
days=1
descriptions=1
shift_size=20
fixed_pos=0
window_left=0
window_top=0
window_right=1664
window_bottom=598
window_type=3
background_color=0
foreground_color=16777215
barup_color=65280
bardown_color=65280
bullcandle_color=0
bearcandle_color=16777215
chartline_color=65280
volumes_color=3329330
grid_color=10061943
askline_color=255
stops_color=255

<window>
height=140
fixed_height=0
<indicator>
name=main
</indicator>
<indicator>
name=Moving Average
period=21
shift=0
method=0
apply=0
color=255
style=0
weight=3
period_flags=0
show_data=1
</indicator>
<indicator>
name=Moving Average
period=60
shift=0
method=0
apply=0
color=65280
style=0
weight=3
period_flags=0
show_data=1
</indicator>
<indicator>
name=Moving Average
period=200
shift=0
method=0
apply=0
color=16777215
style=0
weight=3
period_flags=0
show_data=1
</indicator>
</window>

<window>
height=31
fixed_height=0
<indicator>
name=Custom Indicator
<expert>
name=小鱼\Laguerre-ACS1
flags=339
window_num=1
<inputs>
gamma=0.80000000
MaxBars=1000000
MA=2
</inputs>
</expert>
shift_0=0
draw_0=0
color_0=255
style_0=0
weight_0=5
shift_1=0
draw_1=12
color_1=0
style_1=0
weight_1=0
min=0.00000000
max=1.00000000
levels_color=12632256
levels_style=2
levels_weight=1
level_0=0.85000002
level_1=0.50000000
level_2=0.15000001
period_flags=0
show_data=1
</indicator>
</window>

<window>
height=38
fixed_height=0
<indicator>
name=Custom Indicator
<expert>
name=MACD
flags=339
window_num=2
<inputs>
InpFastEMA=30
InpSlowEMA=65
InpSignalSMA=23
</inputs>
</expert>
shift_0=0
draw_0=2
color_0=16777215
style_0=0
weight_0=3
shift_1=0
draw_1=0
color_1=255
style_1=0
weight_1=3
period_flags=0
show_data=1
</indicator>
</window>

<window>
height=41
fixed_height=0
<indicator>
name=MACD
fast_ema=12
slow_ema=26
macd_sma=9
apply=0
color=16777215
style=0
weight=3
signal_color=255
signal_style=0
signal_weight=3
period_flags=0
show_data=1
</indicator>
</window>
</chart>

