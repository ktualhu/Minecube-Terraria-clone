varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_lightPos;
varying vec2 v_position;

uniform sampler2D u_texture;
void main() {
    int droppfDist = 60;
    float dist = 0;
    dist = pow(abs(v_lightPos.x - v_position.x), 2);
    dist += pow(abs(v_lightPos.y - v_position.y), 2);
    dist = sqrt(dist);

    gl_FragColor = v_color * texture2D(u_texture, v_texCoords) * min(.75, (droppfDist / max(1, dist)));
}
