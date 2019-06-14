varying vec4 v_color;
varying vec2 v_texCoords;

uniform vec2 u_resolution;
uniform float u_time;
uniform sampler2D u_texture;

vec3 hsb2rgb( in vec3 c ){
    vec3 rgb = clamp(abs(mod(c.x*6.0+vec3(0.0,4.0,2.0),
    6.0)-3.0)-1.0,
    0.0,
    1.0 );
    rgb = rgb*rgb*(3.0-2.0*rgb);
    return c.z * mix(vec3(1.0), rgb, c.y);
}

void main()
{
//    vec4 color = texture2D(u_texture, v_texCoords);
//    vec2 st = gl_FragCoord.xy/u_resolution;
//    vec3 color = vec3(0.0);
//
//    // We map x (0.0 - 1.0) to the hue (0.0 - 1.0)
//    // And the y (0.0 - 1.0) to the brightness
//    color = hsb2rgb(vec3(st.x,1.0,st.y));
//
//    gl_FragColor = vec4(color,1.0);

    vec4 color = texture2D(u_texture, v_texCoords);
    float average = (color.r + color.g + color.b) / 3.0;
    gl_FragColor = vec4(1, 1, 1, color.a);
}
