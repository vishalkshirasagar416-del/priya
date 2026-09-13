(function () {
    "use strict";

    const canvas = document.getElementById("avatar-canvas");
    const loading = document.getElementById("loading");
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera(25, 1, 0.1, 100);
    const renderer = new THREE.WebGLRenderer({ canvas: canvas, alpha: true, antialias: true, powerPreference: "low-power" });
    const clock = new THREE.Clock();
    const state = {
        vrm: null, running: true, speaking: false, mouth: 0, mouthTarget: 0,
        blink: 0, blinkUntil: 0, nextBlink: performance.now() + 2600,
        emotion: "neutral", yaw: 0, pitch: 0, distance: 2.4,
        dragging: false, lastX: 0, lastY: 0
    };

    camera.position.set(0, 1.25, state.distance);
    scene.add(new THREE.HemisphereLight(0xffffff, 0x35405c, 1.8));
    const keyLight = new THREE.DirectionalLight(0xffffff, 2.0);
    keyLight.position.set(1, 2, 3);
    scene.add(keyLight);
    renderer.setPixelRatio(Math.min(window.devicePixelRatio || 1, 1.5));
    renderer.setClearColor(0x000000, 0);
    if ("outputColorSpace" in renderer && THREE.SRGBColorSpace) renderer.outputColorSpace = THREE.SRGBColorSpace;

    function resize() {
        const width = Math.max(1, canvas.clientWidth);
        const height = Math.max(1, canvas.clientHeight);
        renderer.setSize(width, height, false);
        camera.aspect = width / height;
        camera.updateProjectionMatrix();
    }

    function getExpressionManager() {
        return state.vrm && (state.vrm.expressionManager || state.vrm.blendShapeProxy);
    }

    function findExpression(names) {
        const manager = getExpressionManager();
        if (!manager) return null;
        for (const name of names) {
            if (manager.getExpression && manager.getExpression(name)) return name;
            if (manager.expressionMap && manager.expressionMap[name]) return name;
            if (manager._blendShapeGroups && manager._blendShapeGroups[name]) return name;
        }
        return null;
    }

    function setExpressionValue(names, value) {
        const manager = getExpressionManager();
        const expression = findExpression(names);
        if (!manager || !expression) return false;
        if (manager.setValue) manager.setValue(expression, value);
        return true;
    }

    function updateExpressionManager() {
        const manager = getExpressionManager();
        if (manager && manager.update) manager.update();
    }

    function setCamera() {
        camera.position.set(Math.sin(state.yaw) * state.distance, 1.25 + Math.sin(state.pitch) * 0.7, Math.cos(state.yaw) * state.distance);
        camera.lookAt(0, 1.15, 0);
    }

    function blink(now) {
        if (now >= state.nextBlink && state.blinkUntil === 0) state.blinkUntil = now + 130;
        if (state.blinkUntil > 0) {
            state.blink = Math.min(1, (state.blinkUntil - now) / 65);
            if (now >= state.blinkUntil) {
                state.blink = 0;
                state.blinkUntil = 0;
                state.nextBlink = now + 2400 + Math.random() * 4200;
            }
        }
        setExpressionValue(["blink"], state.blink);
    }

    function updateMouth(delta) {
        const speakingWave = 0.16 + Math.abs(Math.sin(performance.now() * 0.012)) * 0.42;
        const target = state.speaking ? Math.max(state.mouthTarget, speakingWave) : state.mouthTarget;
        state.mouth += (target - state.mouth) * Math.min(1, delta * 12);
        setExpressionValue(["aa", "A", "a", "mouthOpen", "viseme_aa"], state.mouth);
    }

    function animate() {
        if (!state.running) return;
        requestAnimationFrame(animate);
        const delta = Math.min(clock.getDelta(), 0.1);
        if (state.vrm && state.vrm.update) state.vrm.update(delta);
        blink(performance.now());
        updateMouth(delta);
        updateExpressionManager();
        renderer.render(scene, camera);
    }

    function listExpressions() {
        const manager = getExpressionManager();
        if (!manager) return [];
        if (manager.expressions) return manager.expressions.map(function (item) { return item.name; });
        if (manager.expressionMap) return Object.keys(manager.expressionMap);
        return [];
    }

    function loadAvatar() {
        if (state.vrm) return;
        const loader = new THREE.GLTFLoader();
        loader.register(function (parser) { return new THREE.VRMLoaderPlugin(parser); });
        loader.load("../models/priya.vrm", function (gltf) {
            state.vrm = gltf.userData.vrm;
            if (!state.vrm) throw new Error("VRM loader did not produce a VRM instance");
            scene.add(state.vrm.scene || state.vrm);
            (state.vrm.scene || state.vrm).rotation.y = Math.PI;
            document.body.classList.add("ready");
            if (window.PriyaAvatarBridge) window.PriyaAvatarBridge.onAvatarLoaded(listExpressions().join(","));
        }, undefined, function (error) {
            loading.textContent = "Avatar could not be loaded";
            if (window.PriyaAvatarBridge) window.PriyaAvatarBridge.onRendererError(String(error));
        });
    }

    function setExpression(name) {
        if (!state.vrm) return false;
        const expression = findExpression([name]);
        if (!expression) return false;
        setExpressionValue([expression], 1);
        return true;
    }

    function resetExpression() {
        listExpressions().forEach(function (name) { setExpressionValue([name], 0); });
        state.emotion = "neutral";
    }

    function setEmotion(value) {
        resetExpression();
        const aliases = { happy: ["happy"], sad: ["sad"], angry: ["angry"], surprised: ["surprised", "surprise"], neutral: [] };
        const names = aliases[String(value).toLowerCase()] || [];
        if (names.length) setExpressionValue(names, 1);
        state.emotion = String(value).toLowerCase();
    }

    canvas.addEventListener("pointerdown", function (event) {
        state.dragging = true; state.lastX = event.clientX; state.lastY = event.clientY;
        canvas.setPointerCapture(event.pointerId);
    });
    canvas.addEventListener("pointermove", function (event) {
        if (!state.dragging) return;
        state.yaw += (event.clientX - state.lastX) * 0.008;
        state.pitch = Math.max(-0.55, Math.min(0.55, state.pitch + (event.clientY - state.lastY) * 0.005));
        state.lastX = event.clientX; state.lastY = event.clientY; setCamera();
    });
    canvas.addEventListener("pointerup", function () { state.dragging = false; });
    canvas.addEventListener("wheel", function (event) {
        event.preventDefault(); state.distance = Math.max(1.5, Math.min(4.5, state.distance + event.deltaY * 0.002)); setCamera();
    }, { passive: false });
    window.addEventListener("resize", resize);
    resize(); setCamera();
    window.PriyaAvatar = {
        loadAvatar: loadAvatar,
        setExpression: setExpression,
        setSpeaking: function (value) { state.speaking = Boolean(value); if (!state.speaking) state.mouthTarget = 0; },
        setMouthOpen: function (value) { state.mouthTarget = Math.max(0, Math.min(1, Number(value) || 0)); },
        setEmotion: setEmotion,
        resetExpression: resetExpression,
        pause: function () { state.running = false; },
        resume: function () { if (!state.running) { state.running = true; clock.start(); animate(); } },
        dispose: function () { state.running = false; if (state.vrm) scene.remove(state.vrm.scene || state.vrm); renderer.dispose(); }
    };
    loadAvatar(); animate();
})();